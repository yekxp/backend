package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountDto;
import com.developers.sosyalapp.dto.VerifyEmailDto;

public class VerifyEmailRequest {
    private String email;
    private String token;

    public VerifyEmailRequest() {
    }

    public VerifyEmailRequest(String email, String token) {
        this.email = email;
        this.token = token;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
