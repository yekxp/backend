package com.developers.sosyalapp.service;

import com.developers.sosyalapp.dto.request.CreateAccountPropertiesRequest;
import com.developers.sosyalapp.dto.request.UpdateAccountPropertiesRequest;
import com.developers.sosyalapp.dto.response.ApiResponse;
import com.developers.sosyalapp.mapper.AccountPropertiesMapper;
import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.model.AccountProperties;
import com.developers.sosyalapp.repository.AccountPropertiesRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class AccountPropertiesService {
    private final AccountPropertiesRepository accountPropertiesRepository;
    private final AccountPropertiesMapper accountPropertiesMapper;

    private final AccountService accountService;
    private final JwtService jwtService;
    private static final Logger logger = LoggerFactory.getLogger(AccountPropertiesService.class);

    public AccountPropertiesService(AccountPropertiesRepository accountPropertiesRepository, AccountPropertiesMapper accountPropertiesMapper, AccountService accountService, JwtService jwtService) {
        this.accountPropertiesRepository = accountPropertiesRepository;
        this.accountPropertiesMapper = accountPropertiesMapper;
        this.accountService = accountService;

        this.jwtService = jwtService;
    }

    @Transactional
    public ApiResponse createAccountProperties(CreateAccountPropertiesRequest request) {
        try {
            AccountProperties accountProperties = accountPropertiesMapper.toEntity(request.getAccountProperties());
            accountPropertiesRepository.save(accountProperties);
            logger.info("Account properties created successfully: {}", accountProperties);
            return new ApiResponse<>(true, "Account properties created successfully");
        } catch (Exception e) {
            logger.error("AccountPropertiesService createAccountProperties error: " + e);
            return new ApiResponse<>(false, "Account properties could not be created");
        }
    }

    @Transactional
    public ApiResponse updateAccountProperties(UpdateAccountPropertiesRequest request, String token) {
        try {
            String jwtToken = token.substring(7);
            String accountId = jwtService.extractAccountId(jwtToken);
            Account account = accountService.findAccountById(accountId);

            AccountProperties accountProperties = accountPropertiesMapper.toEntity(request.getAccountProperties());
            accountProperties.setId(account.getAccountProperties().getId());
            accountPropertiesRepository.save(accountProperties);
            logger.info("Account properties updated successfully: {}", accountProperties);
            return new ApiResponse<>(true, "Account properties updated successfully");
        } catch (Exception e) {
            logger.error("AccountPropertiesService updateAccountProperties error: " + e);
            return new ApiResponse<>(false, "Account properties could not be updated");
        }
    }
}
