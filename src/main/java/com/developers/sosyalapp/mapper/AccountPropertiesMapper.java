package com.developers.sosyalapp.mapper;

import com.developers.sosyalapp.dto.AccountPropertiesDto;
import com.developers.sosyalapp.model.AccountProperties;
import org.springframework.stereotype.Component;

@Component
public class AccountPropertiesMapper {
    public AccountPropertiesDto toDto(AccountProperties entity) {
        AccountPropertiesDto dto = new AccountPropertiesDto();
        dto.setAccountId(entity.getAccountId());
        dto.setAge(entity.getAge());
        dto.setCity(entity.getCity());
        dto.setDistrict(entity.getDistrict());
        dto.setIncome(entity.getIncome());
        dto.setSmoking(entity.isSmoking());
        return dto;
    }

    public AccountProperties toEntity(AccountPropertiesDto dto) {
        AccountProperties entity = new AccountProperties();
        entity.setAccountId(dto.getAccountId());
        entity.setAge(dto.getAge());
        entity.setCity(dto.getCity());
        entity.setDistrict(dto.getDistrict());
        entity.setIncome(dto.getIncome());
        entity.setSmoking(dto.isSmoking());
        return entity;
    }
}
