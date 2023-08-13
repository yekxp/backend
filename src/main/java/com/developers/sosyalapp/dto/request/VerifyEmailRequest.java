package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountDto;
import com.developers.sosyalapp.dto.VerifyEmailDto;

public class VerifyEmailRequest {
    private VerifyEmailDto verifyMail;
    public VerifyEmailDto getVerifyMail() {
        return verifyMail;
    }

    public void setVerifyMail(VerifyEmailDto verifyMail) {
        this.verifyMail=verifyMail;

    }
}
