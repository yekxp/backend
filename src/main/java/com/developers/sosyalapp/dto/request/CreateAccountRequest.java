package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountDto;
import com.developers.sosyalapp.dto.VerifyEmailDto;
import com.developers.sosyalapp.model.VerifyEmail;


public class CreateAccountRequest {
     private AccountDto account;

     private VerifyEmailDto verifyemail;
    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account, VerifyEmailDto verifyemail) {
        this.account = account;
        this.verifyemail=verifyemail;

    }

}
