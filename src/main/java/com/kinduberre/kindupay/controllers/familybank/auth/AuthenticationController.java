package com.kinduberre.kindupay.controllers.familybank.auth;


import com.kinduberre.kindupay.models.auth.User;
import com.kinduberre.kindupay.models.dtos.RegistrationDto;
import com.kinduberre.kindupay.models.dtos.TokenRequest;
import com.kinduberre.kindupay.models.dtos.TokenResponse;
import com.kinduberre.kindupay.services.AuthenticationService;
import com.kinduberre.kindupay.services.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/api/v1/auth")
public class AuthenticationController {
    private final JwtService jwtService;

    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<User> register(@RequestBody RegistrationDto registerUserDto) {
        User registeredUser = authenticationService.signup(registerUserDto);

        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> authenticate(@RequestBody TokenRequest loginUserDto) {
        User authenticatedUser = authenticationService.authenticate(loginUserDto);

        String jwtToken = jwtService.generateToken(authenticatedUser);

        TokenResponse loginResponse = new TokenResponse();
        loginResponse.setAccessToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());

        return ResponseEntity.ok(loginResponse);
    }
}
