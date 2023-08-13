package com.developers.sosyalapp.repository;

import com.developers.sosyalapp.model.Account;
import com.developers.sosyalapp.model.VerifyEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyMailRepository extends JpaRepository<VerifyEmail, String> {
    VerifyEmail findByEmail(String email);
}
