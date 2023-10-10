package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import com.developers.hireasenior.model.Account;

import java.util.List;
import java.util.Set;


public interface AccountRepository extends JpaRepository<Account, String>  {
    Account findByEmail(String email);
    List<Account> findAllByTechnologiesIn(Set<Technology> technologies);
    List<Account> findAllByAvailablePeriod(String availablePeriod);
}
