package com.developers.sosyalapp.service;

import com.developers.sosyalapp.dto.request.CreateAccountPropertiesRequest;
import com.developers.sosyalapp.mapper.AccountPropertiesMapper;
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
    private static final Logger logger = LoggerFactory.getLogger(AccountPropertiesService.class);

    public AccountPropertiesService(AccountPropertiesRepository accountPropertiesRepository, AccountPropertiesMapper accountPropertiesMapper) {
        this.accountPropertiesRepository = accountPropertiesRepository;
        this.accountPropertiesMapper = accountPropertiesMapper;
    }

    @Transactional
    public void createAccountProperties(CreateAccountPropertiesRequest request) {
        try {
            AccountProperties accountProperties = accountPropertiesMapper.toEntity(request.getAccountProperties());
            accountPropertiesRepository.save(accountProperties);
            logger.info("Account properties created successfully: {}", accountProperties);
        } catch (Exception e) {
            logger.error("AccountPropertiesService createAccountProperties error: " + e);
        }
    }

    @Transactional
    public void updateAccountProperties(CreateAccountPropertiesRequest request) {
        try {
            AccountProperties accountProperties = accountPropertiesMapper.toEntity(request.getAccountProperties());
            accountPropertiesRepository.save(accountProperties);
            logger.info("Account properties updated successfully: {}", accountProperties);
        } catch (Exception e) {
            logger.error("AccountPropertiesService updateAccountProperties error: " + e);
        }
    }
}
