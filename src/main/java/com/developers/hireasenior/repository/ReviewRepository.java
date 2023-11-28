package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, String> {
    List<Review> findBySeniorId(String seniorId);
}
