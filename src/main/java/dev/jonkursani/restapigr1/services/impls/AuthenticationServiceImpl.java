package dev.jonkursani.restapigr1.services.impls;

import dev.jonkursani.restapigr1.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;

    @Value("${jwt.secret}") // merre vleren prej applicaiton.properties
    private String secretKey;

    private final Long expireTimeInMs = 86400000L; // expire time in milliseconds = 24 hours

    @Override
    public UserDetails authenticate(String email, String password) {
        return null;
    }

    @Override
    public String generateToken(UserDetails userDetails) {
        return "";
    }
}