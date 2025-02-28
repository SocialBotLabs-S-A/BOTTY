package insfrastructure.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // Disable CSRF protection for development purposes.
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // These endpoints are open to everyone.
                        .requestMatchers("/v1/auth/register", "/v1/auth/login").permitAll()
                        // Only users with the ADMIN role can access endpoints under "/v1/admin/**"
                        .requestMatchers("/v1/admin/**").hasRole("ADMIN")
                        // Endpoints under "/v1/moderator/**" require either ADMIN or MODERATOR roles.
                        .requestMatchers("/v1/moderator/**").hasAnyRole("ADMIN", "MODERATOR")
                        // All other requests require authentication.
                        .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }
}
