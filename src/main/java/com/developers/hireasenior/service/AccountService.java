package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.dto.TechnologyDto;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.mapper.AccountMapper;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.AccountRepository;
import com.developers.hireasenior.repository.TechnologyRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class AccountService {

   private final AccountMapper accountMapper;

   private static final Logger logger = LoggerFactory.getLogger(AccountRepository.class);
   private final AccountRepository accountRepository;
   private final TechnologyRepository technologyRepository;


    public ApiResponse<List<AccountDto>> findByTechnologies(Set<TechnologyDto> technologiesDto){
        try{
            Set<String> technologyCodes = technologiesDto
                    .stream()
                    .map(TechnologyDto::getCode)
                    .collect(Collectors.toSet());

            Set<Technology> technologies = technologyRepository.findAllByCodeIn(technologyCodes);
            List<Account> accounts = accountRepository.findAllByTechnologiesIn(technologies);

            if (accounts.isEmpty() || accounts == null){
                logger.debug("Not found any developers.");
                return new ApiResponse<>(true, null, "Not found account with selected technologies.");
            }

            logger.debug("Successfully found developers by technologies.");
            return new ApiResponse<>(true, accountMapper.accountListToDtoList(accounts), "Accounts successfully found.");
        }
        catch (Exception e){
            logger.error("Error occur when trying find account.");
            return new ApiResponse<>(false, null, "Error occur when trying find account.");
        }
    }

    public ApiResponse<List<AccountDto>> findByPeriodTime(Date startedAt, Date endedAt){
        try {

            List<Account> accounts = accountRepository.findAllByAvailablePeriodsInDateRange(startedAt, endedAt);
            if(accounts.isEmpty()){
                logger.debug("Not found any developers.");
                return new ApiResponse<>(true, null, "Not found account in period:" + startedAt + " - " + endedAt);
            }
            logger.debug("Successfully found developers by technologies.");
            List<AccountDto> accountDtos = accountMapper.accountListToDtoList(accounts);
            return new ApiResponse<>(true, accountDtos, "Accounts in period:" + startedAt + " - " + endedAt + " successfully found");

        }
        catch (Exception e){
            logger.error("Error occur when trying find developer in period:" + startedAt + " - " + endedAt);
            return new ApiResponse<>(false, null, "Error occur when trying find developer in period:" + startedAt + " - " + endedAt);
        }
    }

}
