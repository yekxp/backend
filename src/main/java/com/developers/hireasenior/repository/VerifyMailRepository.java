package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.VerifyEmail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerifyMailRepository extends JpaRepository<VerifyEmail, String> {
    VerifyEmail findByEmail(String email);
}
