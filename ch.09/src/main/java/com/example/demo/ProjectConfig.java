package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .addFilterBefore(
                        new RequestValidationFIlter(),
                        BasicAuthenticationFilter.class
                )
                .addFilterBefore(
                        new AuthenticationLoggerFilter(),
                        BasicAuthenticationFilter.class
                )
                .authorizeHttpRequests(
                        auth -> auth
                                .anyRequest().permitAll()
                );
        return http.build();
    }
}
