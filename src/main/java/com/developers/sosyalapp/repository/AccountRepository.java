package com.developers.sosyalapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.developers.sosyalapp.model.Account;


public interface AccountRepository extends JpaRepository<Account, String>  {
    
}
