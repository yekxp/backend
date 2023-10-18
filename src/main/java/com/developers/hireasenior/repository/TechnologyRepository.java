package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.Technology;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface TechnologyRepository extends JpaRepository<Technology, String> {
    Technology findTechnologyByCode(String code);
    Set<Technology> findAllByCodeIn(Set<String> codes);
}
