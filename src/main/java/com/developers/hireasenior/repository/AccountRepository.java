package com.developers.hireasenior.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.developers.hireasenior.model.Account;

import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, String>  {
    Optional<Account> findByEmail(String email);
}
