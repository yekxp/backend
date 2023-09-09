package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.dto.AccountDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class RegistrationResponse {
    private AccountDto account;
}
