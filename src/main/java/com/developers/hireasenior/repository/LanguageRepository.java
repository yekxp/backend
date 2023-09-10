package com.developers.hireasenior.repository;

import com.developers.hireasenior.model.Language;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LanguageRepository extends JpaRepository<Language, String> {
    Optional<Language> findLanguageByCodeOrName(String code, String name);
}
