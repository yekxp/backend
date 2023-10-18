package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;
import com.developers.hireasenior.model.Account;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.sql.Date;
import java.util.List;
import java.util.Set;


public interface AccountRepository extends JpaRepository<Account, String>  {
    Account findByEmail(String email);
    List<Account> findAllByTechnologiesIn(Set<Technology> technologies);
    @Query("SELECT DISTINCT a FROM Account a JOIN a.availablePeriods ap WHERE ap.startedAt <= :startedAt AND ap.endedAt >= :endedAt")
    List<Account> findAllByAvailablePeriodsInDateRange(
            @Param("startedAt") Date startDate,
            @Param("endedAt") Date endDate
    );
}
