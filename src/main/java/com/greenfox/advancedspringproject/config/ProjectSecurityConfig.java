package com.greenfox.advancedspringproject.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.disable())
                .authorizeHttpRequests((authRequest -> authRequest
                        .anyRequest().permitAll()))
                .formLogin(Customizer.withDefaults())
                .httpBasic(httpBasic -> httpBasic.disable());
        return http.build();
    }
}