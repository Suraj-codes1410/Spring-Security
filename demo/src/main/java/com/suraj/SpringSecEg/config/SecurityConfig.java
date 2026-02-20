package com.suraj.SpringSecEg.config;

import org.springframework.context.annotation.*;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import com.suraj.SpringSecEg.Service.MyUserDetailsService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.http.HttpMethod;
@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(MyUserDetailsService userDetailsService) {

        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   DaoAuthenticationProvider authProvider) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .authenticationProvider(authProvider)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/register/**","/saveUser/**","/error").permitAll()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form.disable())
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http,
//                                                   DaoAuthenticationProvider authProvider) throws Exception {
//
//        http
//                .csrf(csrf -> csrf.disable())
//                .authenticationProvider(authProvider)   // ⭐ THIS IS THE MISSING LINK
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/register/**").permitAll()
//                        .requestMatchers("/saveUser/**").permitAll()
//                        .requestMatchers("/error").permitAll()
//                        .anyRequest().authenticated()
//                )
//                .httpBasic(Customizer.withDefaults())
//                .exceptionHandling(e -> e.authenticationEntryPoint(
//                        (req, res, ex) -> res.sendError(401, "Unauthorized")
//                ))
//                .formLogin(form -> form.disable());
//
//        return http.build();
//    }
}


//.authenticationProvider(authProvider)   // 🔥 THIS LINE IS THE KEY
//                .authorizeHttpRequests(auth -> auth
//        .requestMatchers("/register", "/saveUser").permitAll()
//                        .anyRequest().authenticated()
//                )