package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.config.security;

import com.nimbusds.jose.jwk.JWKSet;
import com.nimbusds.jose.jwk.RSAKey;
import com.nimbusds.jose.jwk.source.ImmutableJWKSet;
import com.nimbusds.jose.jwk.source.JWKSource;
import com.nimbusds.jose.proc.SecurityContext;
import com.socialbotlabs.serverbot.socialbotlabs_bot.user_module.application.service.UserOauth2Service;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;
import org.springframework.security.oauth2.core.oidc.endpoint.OidcParameterNames;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.server.authorization.OAuth2TokenType;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configuration.OAuth2AuthorizationServerConfiguration;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.oauth2.server.authorization.settings.AuthorizationServerSettings;
import org.springframework.security.oauth2.server.authorization.token.JwtEncodingContext;
import org.springframework.security.oauth2.server.authorization.token.OAuth2TokenCustomizer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class AuthorizationSecurityConfig {

    private static final int KEY_SIZE = 2048;

    private final UserOauth2Service userOauth2Service;
    private final String loginPage;
    private final String logoutPage;

    public AuthorizationSecurityConfig(UserOauth2Service userOauth2Service,
                                       @Value("${social.botty.default.login.page}") String loginPage,
                                       @Value("${social.botty.default.logout.page}") String logoutPage) {
        this.userOauth2Service = userOauth2Service;
        this.loginPage = loginPage;
        this.logoutPage = logoutPage;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain authSecurityFilterChain(HttpSecurity http) throws Exception {
        OAuth2AuthorizationServerConfigurer authorizationServerConfigurer =
            OAuth2AuthorizationServerConfigurer.authorizationServer();

        // Enable OpenID Connect 1.0
        http.securityMatcher(authorizationServerConfigurer.getEndpointsMatcher())
            .with(authorizationServerConfigurer, oAuth2AuthorizationServerConfigurer ->
                oAuth2AuthorizationServerConfigurer.oidc(Customizer.withDefaults()))
            .authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
                authorizationManagerRequestMatcherRegistry
                    .anyRequest().authenticated())
            // Redirect to the login page when not authenticated from the
            // authorization endpoint
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.defaultAuthenticationEntryPointFor(
                    new LoginUrlAuthenticationEntryPoint(loginPage),
                    new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                ))
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth ->
                auth.requestMatchers("/auth/**","/client/**", loginPage,logoutPage).permitAll()
                    .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage(loginPage)
                .permitAll()
            )
            .logout(logout -> logout.logoutUrl("/logout")
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout", "POST"))
                .logoutSuccessUrl(loginPage+"?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .clearAuthentication(true))
            .oauth2Login(httpSecurityOAuth2LoginConfigurer ->{
                httpSecurityOAuth2LoginConfigurer.loginPage(loginPage);
                FederatedIdentityAuthenticationSuccessHandler fed =
                    new FederatedIdentityAuthenticationSuccessHandler();
                fed.setOAuth2UserHandler(new UserRepositoryOAuth2UserHandler(userOauth2Service));
                httpSecurityOAuth2LoginConfigurer.successHandler(fed);
                })
            .csrf(AbstractHttpConfigurer::disable);
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public OAuth2TokenCustomizer<JwtEncodingContext> tokenCustomizer(){
        return (JwtEncodingContext context) -> {
            Authentication authentication = context.getPrincipal();
            if (context.getTokenType().getValue().equals(OidcParameterNames.ID_TOKEN)){
                context.getClaims().claim("token_type","id token");
            }
            if (context.getTokenType().equals(OAuth2TokenType.ACCESS_TOKEN)){
                context.getClaims().claim("token_type","access token");
                Set<String> authorities = authentication.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());
                context.getClaims().claim("authorities",authorities)
                    .claim("username",authentication.getName());
            }
        };
    }

    @Bean
    public JWKSource<SecurityContext> jwkSource() {
        KeyPair keyPair = generateRsaKey();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        RSAKey rsaKey = new RSAKey.Builder(publicKey)
            .privateKey(privateKey)
            .keyID(UUID.randomUUID().toString())
            .build();
        JWKSet jwkSet = new JWKSet(rsaKey);
        return new ImmutableJWKSet<>(jwkSet);
    }

    private static KeyPair generateRsaKey() {
        KeyPair keyPair;
        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(KEY_SIZE);
            keyPair = keyPairGenerator.generateKeyPair();
        } catch (Exception ex) {
            throw new IllegalStateException(ex);
        }
        return keyPair;
    }

    @Bean
    public JwtDecoder jwtDecoder(JWKSource<SecurityContext> jwkSource) {
        return OAuth2AuthorizationServerConfiguration.jwtDecoder(jwkSource);
    }

    @Bean
    public AuthorizationServerSettings authorizationServerSettings() {
        return AuthorizationServerSettings.builder()
            .issuer("http://localhost:8088/authorization-server")
            .build();
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

}
