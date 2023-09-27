package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.SessionRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SessionRequestRepository extends JpaRepository<SessionRequest, String> {
    List<SessionRequest> findByJuniorId(String juniorId);
    List<SessionRequest> findBySeniorId(String seniorId);
}
