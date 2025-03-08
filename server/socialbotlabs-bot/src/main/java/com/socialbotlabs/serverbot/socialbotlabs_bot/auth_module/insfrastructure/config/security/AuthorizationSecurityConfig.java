package com.socialbotlabs.serverbot.socialbotlabs_bot.auth_module.insfrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.MediaType;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.oauth2.server.authorization.config.annotation.web.configurers.OAuth2AuthorizationServerConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.MediaTypeRequestMatcher;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

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
                authorizationManagerRequestMatcherRegistry.anyRequest().authenticated())
            // Redirect to the login page when not authenticated from the
            // authorization endpoint
            .exceptionHandling(httpSecurityExceptionHandlingConfigurer ->
                httpSecurityExceptionHandlingConfigurer.defaultAuthenticationEntryPointFor(
                    new LoginUrlAuthenticationEntryPoint("/login"),
                    new MediaTypeRequestMatcher(MediaType.TEXT_HTML)
                ));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain webSecurityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/auth/**", "/client/**", "/login").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin(form -> form
                .loginPage("/login")
                .permitAll()
            )
            .oauth2Login(oauth2Login -> {
                oauth2Login.loginPage("/login");
                FederatedIdentityAuthenticationSuccessHandler fed = new FederatedIdentityAuthenticationSuccessHandler();
                fed.setOAuth2UserHandler(new UserRepositoryOAuth2UserHandler(googleUserRepository));
                oauth2Login.successHandler(fed);
            })
            .csrf(csrf -> csrf.ignoringRequestMatchers("/auth/**", "/client/**"));
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
