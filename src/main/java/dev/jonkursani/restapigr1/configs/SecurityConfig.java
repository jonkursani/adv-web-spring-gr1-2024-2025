package dev.jonkursani.restapigr1.configs;

import dev.jonkursani.restapigr1.entities.User;
import dev.jonkursani.restapigr1.repositories.UserRepository;
import dev.jonkursani.restapigr1.security.AppUserDetailsService;
import dev.jonkursani.restapigr1.security.JwtAuthenticationFilter;
import dev.jonkursani.restapigr1.services.AuthenticationService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.time.LocalDateTime;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(AuthenticationService authenticationService) {
        return new JwtAuthenticationFilter(authenticationService);
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http,
                                                   JwtAuthenticationFilter jwtAuthenticationFilter) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        // requestMatchers cilat url jon public qe nuk duhet auth
                        .requestMatchers(HttpMethod.POST, "/api/v1/auth/login").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/departments/**").permitAll()
                        // per te gjitha routes tjera qe nuk i keni specifiku lart duhet auth
                        .anyRequest().authenticated()
                )
                .csrf(csrf -> csrf.disable()) // cross-site request forgery
                // kur perdorim jwt token nuk na duhet session management per ate e vendosim si stateless
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository repository) {
        AppUserDetailsService appUserDetailsService = new AppUserDetailsService(repository);

        String email = "user@test.com";
        repository.findByEmail(email)
                .orElseGet(() -> {
                    var newUser = User.builder()
                            .name("User")
                            .email(email)
                            .password(passwordEncoder().encode("password"))
                            .build();

                    return repository.save(newUser);
                });

        return appUserDetailsService;
    }
}