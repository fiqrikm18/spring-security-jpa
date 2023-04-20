package com.example.jpaauthspring.config;

import com.example.jpaauthspring.provider.AuthProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class AuthenticationSecurityConfig {

    final AuthProvider authProvider;

    public AuthenticationSecurityConfig(AuthProvider authProvider) {
        this.authProvider = authProvider;
    }

    @Bean
    @Order(1)
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(auth -> {
            auth.requestMatchers("/users/create**").permitAll();
            auth.anyRequest().authenticated();
        })
                .csrf().disable()
                .httpBasic(Customizer.withDefaults());
        return http.build();
    }

}
