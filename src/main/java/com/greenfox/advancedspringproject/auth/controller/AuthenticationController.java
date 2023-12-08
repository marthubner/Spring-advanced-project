package com.greenfox.advancedspringproject.auth.controller;

import com.greenfox.advancedspringproject.auth.service.AuthenticationService;
import com.greenfox.advancedspringproject.auth.model.RegisterRequest;
import com.greenfox.advancedspringproject.auth.model.AuthenticationRequest;
import com.greenfox.advancedspringproject.auth.model.AuthenticationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }
}

