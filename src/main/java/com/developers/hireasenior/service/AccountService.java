package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.dto.TechnologyDto;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.mapper.AccountMapper;
import com.developers.hireasenior.mapper.TechnologyMapper;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.AccountRepository;
import com.developers.hireasenior.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@AllArgsConstructor
public class AccountService {

   private final TechnologyMapper technologyMapper;
   private final AccountMapper accountMapper;

   private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);
   private final AccountRepository accountRepository;
   private final TechnologyRepository technologyRepository;


    public ApiResponse<List<AccountDto>> findByTechnologies(Set<TechnologyDto> technologiesDto){
        try{
            Set<Technology> technologies = technologyMapper.technologyDtoSetToTechnologySet(technologiesDto);
            List<Account> accounts = accountRepository.findAllByTechnologiesIn(technologies);
            if (accounts.isEmpty() || accounts == null){
                return new ApiResponse<>(true, accountMapper.accountListToDtoList(accounts), "Not found account with your technologies.");
            }

            return new ApiResponse<>(true, accountMapper.accountListToDtoList(accounts), "Accounts successfully found.");
        }catch (Exception e){
            logger.error("Error occur when trying find account.");
            return new ApiResponse<>(false, null, "Error occur when trying find account.");
        }

    }

    public ApiResponse<AccountDto> findByPeriodTime(String availablePeriod){

        List<Account> account = accountRepository.findAllByAvailablePeriod(availablePeriod);

        return new ApiResponse<>(true, null, "Account created successfully.");
    }

}
