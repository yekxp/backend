package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountDto;
import com.developers.sosyalapp.dto.VerifyEmailDto;

public class VerifyEmailRequest {

    private VerifyEmailDto verifyemail;
    public VerifyEmailDto getVerifyemail() {
        return verifyemail;
    }

    public void setVerifyemail( VerifyEmailDto verifyemail) {

        this.verifyemail=verifyemail;

    }
}
