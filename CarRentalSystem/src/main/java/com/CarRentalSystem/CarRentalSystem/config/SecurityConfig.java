package com.CarRentalSystem.CarRentalSystem.config;

import com.CarRentalSystem.CarRentalSystem.service.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService; // Dependency on CustomUserDetailsService

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        // 1. Public endpoints
                        .requestMatchers("/api/auth/**").permitAll()
                        .requestMatchers("/api/cars/available").permitAll()

                        // 2. Admin-only endpoints
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")        // Secures all AdminController endpoints
                        .requestMatchers("/api/cars/**").hasRole("ADMIN")         // Secures all CarController endpoints (POST, PUT, DELETE)
                        .requestMatchers("/api/booking/all").hasRole("ADMIN")     // CORRECTED: Restricts global data to Admin
                        .requestMatchers("/api/booking/active/all").hasRole("ADMIN")

                        // 3. User & Admin access
                        .requestMatchers("/api/booking/**").hasAnyRole("USER", "ADMIN") // Applies to /me, /active, /history, /cancel

                        // 4. Any other endpoints must be authenticated
                        .anyRequest().authenticated()
                )
                .httpBasic(Customizer.withDefaults());

        return http.build();
    }
}