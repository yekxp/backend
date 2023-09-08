package com.developers.hireasenior.mapper;

import org.springframework.stereotype.Component;

import com.developers.hireasenior.dto.AccountDto;

import com.developers.hireasenior.model.Account;


@Component
public class AccountMapper {
     public AccountDto toDto(Account entity) {
        AccountDto dto = new AccountDto();
        dto.setFirstName(entity.getFirstName());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        dto.setVerified(entity.getVerified());
        dto.setRole(entity.getRole());
        dto.setTitle(entity.getTitle());
        dto.setHourlyPrice(entity.getHourlyPrice());
        dto.setCurrency(entity.getCurrency());
        return dto;
    }

    public Account toEntity(AccountDto dto){
       Account entity=new Account();
       entity.setFirstName(dto.getFirstName());
       entity.setEmail(dto.getEmail());
       entity.setPassword(dto.getPassword());
       entity.setVerified(dto.getVerified());
       entity.setRole(dto.getRole());
       entity.setTitle(dto.getTitle());
       entity.setHourlyPrice(dto.getHourlyPrice());
       entity.setCurrency(dto.getCurrency());
       return entity;
    }
}
