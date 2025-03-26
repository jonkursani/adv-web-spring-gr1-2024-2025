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

import static dev.jonkursani.restapigr1.entities.Permission.*;
import static dev.jonkursani.restapigr1.entities.Role.ADMIN;
import static dev.jonkursani.restapigr1.entities.Role.MANAGER;

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

                        // request matchers authorization
                        // niveli i validimit te komplet routes
                        // hasRole pranon vetem nje rol, hasAnyRole pranon 2 e me shume
                        .requestMatchers("/api/v1/management/**").hasAnyRole(ADMIN.name(), MANAGER.name())

                        // validoni endpoints specifik
                        // hasAuthority vetem nje permission, hasAnyAuthority 2 e me shume
                        .requestMatchers(HttpMethod.GET, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_READ.name(), MANAGER_READ.name())
                        .requestMatchers(HttpMethod.POST, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.PUT, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/management/**")
                            .hasAnyAuthority(ADMIN_WRITE.name(), MANAGER_WRITE.name())


//                        .requestMatchers("/api/v1/admin").hasRole(ADMIN.name())
//                        .requestMatchers(HttpMethod.GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
//                        .requestMatchers(HttpMethod.POST, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())
//                        .requestMatchers(HttpMethod.DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_WRITE.name())

//                        .requestMatchers("api/v1/employees").hasRole(ADMIN.name(), EMPLOYEE.name())

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