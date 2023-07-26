package com.developers.sosyalapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.developers.sosyalapp.dto.request.CreateAccountRequest;
import com.developers.sosyalapp.dto.request.CreateExampleRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateAccountResponse;
import com.developers.sosyalapp.dto.response.CreateExampleResponse;
import com.developers.sosyalapp.service.AccountService;

@RestController
@RequestMapping("/api/v1/account")
public class AccountController {
    
    private final AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateAccountResponse>> createAccount(@RequestBody CreateAccountRequest request) {
        return ResponseEntity.ok(accountService.createAccount(request));
    }
}
