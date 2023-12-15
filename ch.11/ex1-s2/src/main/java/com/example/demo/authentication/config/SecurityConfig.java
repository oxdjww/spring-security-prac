package com.example.demo.authentication.config;

import com.example.demo.authentication.filters.InitialAuthenticationFilter;
import com.example.demo.authentication.filters.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final InitialAuthenticationFilter initialAuthenticationFilter;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public SecurityConfig(InitialAuthenticationFilter initialAuthenticationFilter,
                          JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.initialAuthenticationFilter = initialAuthenticationFilter;
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(
                AbstractHttpConfigurer::disable
        );

        http
                .addFilterAt(
                        initialAuthenticationFilter,
                        BasicAuthenticationFilter.class
                )
                .addFilterAfter(
                        jwtAuthenticationFilter,
                        BasicAuthenticationFilter.class
                );

        http
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest()
                                .authenticated()
                );

        return http.build();
    }
}
