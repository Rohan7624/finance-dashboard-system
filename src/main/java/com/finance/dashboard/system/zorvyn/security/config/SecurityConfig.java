package com.finance.dashboard.system.zorvyn.security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http

            .csrf(csrf -> csrf.disable())

            // ❌ no session (best for REST APIs)
            .sessionManagement(session ->
                    session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            )

            .authorizeHttpRequests(auth -> auth
                    // ✅ Swagger (VERY IMPORTANT)
                    .requestMatchers(
                            "/swagger-ui/**",
                            "/v3/api-docs/**",
                            "/swagger-ui.html"
                    ).permitAll()
                // 🔓 Public endpoints
                .requestMatchers("/auth/**").permitAll()

                // 👁 Viewer access
                .requestMatchers("/records", "/dashboard/**")
                .hasAnyRole("VIEWER", "ANALYST", "ADMIN")

                // ✏ Analyst access
                .requestMatchers("/records/type", "/records/category")
                .hasAnyRole("ANALYST", "ADMIN")

                // 🔐 Admin only
                .requestMatchers("/users/**", "/records/**")
                .hasRole("ADMIN")

                .anyRequest().authenticated()
            )

            // 🔐 Basic auth (for testing only)
            .httpBasic(httpBasic -> {});

        return http.build();
    }
}