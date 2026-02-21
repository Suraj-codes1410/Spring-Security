package com.suraj.SpringSecEg.config;

import com.suraj.SpringSecEg.Service.MyUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    // BCrypt encoder
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Connect DB users
    @Bean
    public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService service) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(service);
        provider.setPasswordEncoder(passwordEncoder());
        return provider;
    }

    // ⭐ Chain 1 → PUBLIC endpoints (NO authentication at all)
    @Bean
    @Order(1)
    public SecurityFilterChain publicChain(HttpSecurity http) throws Exception {
        http
                .securityMatcher("/register", "/test")
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll());

        return http.build();
    }

    // ⭐ Chain 2 → PROTECTED endpoints (students API)
    @Bean
    @Order(2)
    public SecurityFilterChain privateChain(HttpSecurity http,
                                            DaoAuthenticationProvider provider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(provider)
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated()
                )
                .httpBasic(basic -> {});

        return http.build();
    }
}