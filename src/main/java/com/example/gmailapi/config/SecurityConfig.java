package com.example.gmailapi.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SessionAuthFilter sessionAuthFilter;

    public SecurityConfig(SessionAuthFilter sessionAuthFilter) {
        this.sessionAuthFilter = sessionAuthFilter;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/", "/auth/**",
                        "/css/**", "/js/**", "/images/**",
                        "/WEB-INF/views/**"
                ).permitAll()
                .anyRequest().authenticated()
            )
            .addFilterBefore(sessionAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .formLogin(login -> login.disable())
            .httpBasic(basic -> basic.disable());

        return http.build();
    }
}
