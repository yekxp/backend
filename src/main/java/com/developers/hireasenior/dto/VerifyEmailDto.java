package com.developers.hireasenior.dto;

import com.developers.hireasenior.model.Account;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VerifyEmailDto {
    private String email;
    private String token;
    private Account account;
}
