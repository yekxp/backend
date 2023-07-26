package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountDto;


public class CreateAccountRequest {
     private AccountDto account;

    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }
}
