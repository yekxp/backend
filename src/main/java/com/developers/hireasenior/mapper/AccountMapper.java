package com.developers.hireasenior.mapper;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.model.Account;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);
    AccountDto accountEntityToDto(Account account);
    Account accountDtoToAccount(AccountDto accountDto);

    List<AccountDto> accountListToDtoList(List<Account> accounts);
    List<Account> accountDtoListToAccountList(List<AccountDto> accountDtos);
}

