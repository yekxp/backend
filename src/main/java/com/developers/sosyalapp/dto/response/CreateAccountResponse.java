package com.developers.sosyalapp.dto.response;

import com.developers.sosyalapp.dto.AccountDto;
import com.developers.sosyalapp.dto.VerifyEmailDto;

public class CreateAccountResponse {
    private AccountDto account;

    private VerifyEmailDto verifyemail;
    public AccountDto getAccount() {
        return account;
    }

    public void setAccount(AccountDto account) {
        this.account = account;
    }


    public CreateAccountResponse(AccountDto account,VerifyEmailDto verifyemail) {
        this.account = account;
        this.verifyemail=verifyemail;
    }

}
