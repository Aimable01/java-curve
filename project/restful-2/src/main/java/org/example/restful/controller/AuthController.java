package org.example.restful.controller;

import jakarta.validation.Valid;
import org.example.restful.dto.ApiResponse;
import org.example.restful.dto.AuthResponse;
import org.example.restful.dto.LoginRequest;
import org.example.restful.dto.RegisterRequest;
import org.example.restful.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = authService.authenticateUser(
                loginRequest.getUsername(),
                loginRequest.getPassword());

        return ResponseEntity.ok(ApiResponse.success("Login successful", authResponse));
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterRequest registerRequest) {
        AuthResponse authResponse = authService.registerUser(
                registerRequest.getUsername(),
                registerRequest.getPassword(),
                registerRequest.getFullName(),
                registerRequest.getEmail());

        return ResponseEntity.ok(ApiResponse.success("User registered successfully", authResponse));
    }
}
