package com.developers.sosyalapp.controller;

import com.developers.sosyalapp.dto.request.UpdateAccountPropertiesRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.service.AccountPropertiesService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/v1/account-properties")
public class AccountPropertiesController {
    private final AccountPropertiesService accountPropertiesService;


    public AccountPropertiesController(AccountPropertiesService accountPropertiesService) {
        this.accountPropertiesService = accountPropertiesService;
    }

    @PutMapping("/update")
    public ResponseEntity<ApiResponse> updateAccountProperties(@RequestBody UpdateAccountPropertiesRequest request,
                                                               @RequestHeader("Authorization") String token) throws AccountNotFoundException {
        return ResponseEntity.ok(accountPropertiesService.updateAccountProperties(request, token));
    }
}
