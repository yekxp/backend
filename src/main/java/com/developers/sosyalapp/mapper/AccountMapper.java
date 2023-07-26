package com.developers.sosyalapp.mapper;

import org.springframework.stereotype.Component;

import com.developers.sosyalapp.dto.AccountDto;

import com.developers.sosyalapp.model.Account;


@Component
public class AccountMapper {
     public AccountDto toDto(Account entity) {
        AccountDto dto = new AccountDto();
        dto.setId(entity.getId());
        dto.setUsername(entity.getUsername());
        dto.setEmail(entity.getEmail());
        dto.setPassword(entity.getPassword());
        return dto;
    }

    public Account toEntity(AccountDto dto){
       Account entity=new Account();
       entity.setEmail(dto.getEmail());
       entity.setId(dto.getId());
       entity.setPassword(dto.getPassword());
       entity.setUsername(dto.getUsername());
       return entity;
    }
}
