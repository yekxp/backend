package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.LoginRequest;
import com.developers.hireasenior.dto.response.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.hireasenior.dto.request.RegistrationRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.RegistrationResponse;
import com.developers.hireasenior.service.AuthService;

@RestController
@RequestMapping("/api/v1/auth")
@AllArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<RegistrationResponse>> register(@Valid @RequestBody RegistrationRequest request) {
        return ResponseEntity.ok(authService.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthenticationResponse>> login(@Valid @RequestBody LoginRequest request) throws Exception {
        return ResponseEntity.ok(authService.login(request));
    }
}
