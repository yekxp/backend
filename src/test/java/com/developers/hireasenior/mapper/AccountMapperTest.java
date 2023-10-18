package com.developers.hireasenior.mapper;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.model.Account;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AccountMapperTest {

    private AccountMapper accountMapper = AccountMapper.INSTANCE;
    @Mock
    private Account john;
    @Mock
    private Account bob;

    @BeforeEach
    void setUp() {
        john = new Account();
        john.setFirstName("John");
        john.setEmail("john@example.com");

        bob = new Account();
        bob.setFirstName("Bob");
        bob.setEmail("bob@example.com");
    }

    @Test
    void testAccountEntityToDto() {
        AccountDto accountDto = accountMapper.accountEntityToDto(john);

        assertEquals(john.getFirstName(), accountDto.getFirstName());
        assertEquals(john.getEmail(), accountDto.getEmail());
    }

    @Test
    void testAccountDtoToAccount() {
        AccountDto alice = new AccountDto();
        alice.setFirstName("Alice");
        alice.setEmail("alice@example.com");

        Account account = accountMapper.accountDtoToAccount(alice);

        assertEquals(alice.getFirstName(), account.getFirstName());
        assertEquals(alice.getEmail(), account.getEmail());
    }

    @Test
    void testAccountListToDtoList() {
        List<Account> accountList = new ArrayList<>();

        accountList.add(bob);
        accountList.add(john);

        List<AccountDto> accountDtoList = accountMapper.accountListToDtoList(accountList);

        assertEquals(accountList.size(), accountDtoList.size());
    }

    @Test
    void testAccountDtoListToAccountList() {
        List<AccountDto> accountDtoList = new ArrayList<>();

        AccountDto dave = new AccountDto();
        dave.setFirstName("Dave");
        dave.setEmail("dave@example.com");

        AccountDto eve = new AccountDto();
        eve.setFirstName("Eve");
        eve.setEmail("eve@example.com");
        accountDtoList.add(dave);
        accountDtoList.add(eve);

        List<Account> accountList = accountMapper.accountDtoListToAccountList(accountDtoList);

        assertEquals(accountDtoList.size(), accountList.size());
    }
}
