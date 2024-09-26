package com.ssk.SpringSecurityWithDAO.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity  // Enables Spring Security's web security support
public class SecurityConfig {
    private final UserDetailsService userDetailsService;  // UserDetailsService to load user-specific data

    // Constructor for injecting UserDetailsService
    public SecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    // Bean for BCryptPasswordEncoder, used for password hashing
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder(12);  // 2^12 rounds for BCrypt
    }

    // AuthenticationProvider bean for configuring authentication
    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);  // Set the user details service to use
        provider.setPasswordEncoder(bCryptPasswordEncoder());  // Set the password encoder
        return provider;  // Return the configured provider
    }

    // SecurityFilterChain bean for configuring HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // Disable CSRF protection for stateless APIs (typically used for REST APIs with JWT)
        http.csrf(AbstractHttpConfigurer::disable);

        // Configure URL access rules
        http.authorizeHttpRequests(req ->
                req
                        .requestMatchers("/register", "/login").permitAll()  // Allow access to login and register endpoints
                        .requestMatchers("/admin").hasRole("ADMIN")
                        .requestMatchers("/manager").hasRole("MANAGER")
                        .requestMatchers("/employee").hasRole("EMPLOYEE")
                        .anyRequest().authenticated()  // Require authentication for all other endpoints
        );

        // Configure session management to be stateless (important for JWT usage)
        http.sessionManagement(config -> config.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Use HTTP Basic authentication (can later be replaced with JWT authentication)
        http.httpBasic(Customizer.withDefaults());

        return http.build();  // Build and return the security filter chain
    }
}
