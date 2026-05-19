package com.example.computerlab.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.ignoringRequestMatchers("/api/**"))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/login", "/register", "/css/**", "/api/**").permitAll()
                        .requestMatchers("/users/**").hasRole("ADMIN")
                        .requestMatchers("/computers/new", "/computers/*/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/computers/**").hasRole("ADMIN")
                        .requestMatchers("/labrooms/new", "/labrooms/*/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/labrooms/**").hasRole("ADMIN")
                        .requestMatchers("/operating-systems/new", "/operating-systems/*/edit").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/operating-systems/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.POST, "/maintenance-tickets/*/status").hasRole("ADMIN")
                        .requestMatchers(
                                "/",
                                "/dashboard",
                                "/computers/**",
                                "/labrooms/**",
                                "/operating-systems/**",
                                "/bookings/**",
                                "/maintenance-tickets/**"
                        ).authenticated()
                        .anyRequest().authenticated()
                )
                .formLogin(form -> form
                        .loginPage("/login")
                        .loginProcessingUrl("/login")
                        .usernameParameter("email")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/dashboard", true)
                        .permitAll()
                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll()
                );

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
