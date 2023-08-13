package com.developers.sosyalapp.controller;

import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.service.VerificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/verification")
public class VerificationController {
    private final VerificationService verificationService;

    public VerificationController(VerificationService verificationService) {
        this.verificationService = verificationService;
    }

    @GetMapping("/verify-mail")
    public ResponseEntity<ApiResponse> verifyMail(@RequestParam String email, @RequestParam String token) {
        return ResponseEntity.ok(verificationService.verifyEmail(email, token));
    }
}
