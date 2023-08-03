package com.developers.sosyalapp.service;

import org.springframework.stereotype.Service;

import com.developers.sosyalapp.dto.request.CreateAccountRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateAccountResponse;
import com.developers.sosyalapp.mapper.AccountMapper;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
    }

    public ApiResponse<CreateAccountResponse> createAccount(CreateAccountRequest request) {
        try {
            Account account = accountRepository.save(accountMapper.toEntity(request.getAccount()));
            return new ApiResponse<>(true, new CreateAccountResponse(accountMapper.toDto(account)),
                    "Account created successfully.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Account could not be created.");
        }
    }

}
