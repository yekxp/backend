package com.developers.sosyalapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.sosyalapp.model.Account;


public interface AccountRepository extends JpaRepository<Account, String>  {
    public List<Account> findByEmailOrUsername(String email, String username);
}
