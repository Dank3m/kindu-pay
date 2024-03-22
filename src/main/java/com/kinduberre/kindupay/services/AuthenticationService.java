package com.kinduberre.kindupay.services;

import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.dtos.RegistrationDto;
import com.kinduberre.kindupay.models.dtos.TokenRequest;
import com.kinduberre.kindupay.repositories.common.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthenticationManager authenticationManager;

    public AuthenticationService(
            UserRepository userRepository,
            AuthenticationManager authenticationManager,
            PasswordEncoder passwordEncoder
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User signup(RegistrationDto input) {
        User user = new User();
            user.setFullName(input.getFullName());
            user.setUsername(input.getClientId());
            user.setPassword(input.getClientSecret());
        return userRepository.save(user);
    }

    public User authenticate(TokenRequest input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getClientId(),
                        input.getClientSecret()
                )
        );

        return userRepository.findByUsername(input.getClientId())
                .orElseThrow();
    }
}
