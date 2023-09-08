package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.dto.AccountDto;

public class RegistrationResponse {
    private AccountDto account;

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }


    public RegistrationResponse(AccountDto account) {
        this.account = account;
    }

}
