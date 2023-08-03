package com.developers.sosyalapp.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.developers.sosyalapp.dto.request.CreateAccountRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.dto.response.CreateAccountResponse;
import com.developers.sosyalapp.exception.EmailOrUsernameAlreadyExistsException;
import com.developers.sosyalapp.mapper.AccountMapper;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.repository.AccountRepository;

@Service
public class AccountService {
    private final AccountRepository accountRepository;
    private final AccountMapper accountMapper;
    private final PasswordEncoder passwordEncoder;

    public AccountService(AccountRepository accountRepository, AccountMapper accountMapper,
            PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.accountMapper = accountMapper;
        this.passwordEncoder = passwordEncoder;

    }

    public ApiResponse<CreateAccountResponse> createAccount(CreateAccountRequest request) {
        try {
            List<Account> foundAccounts = accountRepository.findByEmailOrUsername(request.getAccount().getEmail(),
                    request.getAccount().getUsername());
            if (foundAccounts.size() > 0) {
                throw new EmailOrUsernameAlreadyExistsException("Email or username already exists.");
            }

            String ecryptedPassword = passwordEncoder.encrypt(request.getAccount().getPassword());
            Account newAccount = accountMapper.toEntity(request.getAccount());
            newAccount.setPassword(ecryptedPassword);

            Account account = accountRepository.save(newAccount);
            return new ApiResponse<>(true, new CreateAccountResponse(accountMapper.toDto(account)),
                    "Account created successfully.");
        } catch(EmailOrUsernameAlreadyExistsException e) {
            return new ApiResponse<>(false, null, e.getMessage());
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Account could not be created.");
        }
    }

}
