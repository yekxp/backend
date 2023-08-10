package com.developers.sosyalapp.dto.request;

import com.developers.sosyalapp.dto.AccountPropertiesDto;

public class CreateAccountPropertiesRequest {
    AccountPropertiesDto accountProperties;

    public AccountPropertiesDto getAccountProperties() {
        return accountProperties;
    }

    public void setAccountProperties(AccountPropertiesDto accountProperties) {
        this.accountProperties = accountProperties;
    }
}
