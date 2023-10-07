package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.AddLanguageRequest;
import com.developers.hireasenior.dto.request.DeleteLanguageRequest;
import com.developers.hireasenior.dto.request.UpdateLanguageRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.exception.ResourceAlreadyExistsException;
import com.developers.hireasenior.exception.ResourceNotFoundException;
import com.developers.hireasenior.model.Language;
import com.developers.hireasenior.model.Technology;
import com.developers.hireasenior.repository.LanguageRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LanguageService {
    private final LanguageRepository languageRepository;
    private static final Logger logger = LoggerFactory.getLogger(LanguageService.class);

    public ApiResponse<AddLanguageResponse> addLanguage(AddLanguageRequest request) {
        try {
            Optional<Language> foundLanguage = languageRepository.findLanguageByCodeOrName(request.getCode(), request.getName());
            if(foundLanguage.isPresent()) {
                throw new ResourceAlreadyExistsException("Language already exists.");
            }

            Language language = new Language();
            language.setName(request.getName());
            language.setCode(request.getCode());

            languageRepository.save(language);
            logger.info("Language created: {}", language.getName());
            return new ApiResponse<>(true, new AddLanguageResponse(language), "Language successfully created.");
        } catch (ResourceAlreadyExistsException e) {
            logger.error("Language already exists: {}", request.getName());
            return new ApiResponse<>(false, null, "Language already exists.");
        } catch (Exception e) {
            logger.error("Error creating language: {}", e.getMessage());
            return new ApiResponse<>(false, null, "Error creating language.");
        }
    }

    public ApiResponse<ListLanguagesResponse> listLanguages() {
        try {
            List<Language> languages = languageRepository.findAll();
            logger.info("Languages found: {}", languages.size());
            return new ApiResponse<>(true, new ListLanguagesResponse(languages), "Languages successfully listed.");
        } catch (Exception e) {
            logger.error("Error finding languages: {}", e.getMessage());
            return new ApiResponse<>(false, null, "Error finding languages.");
        }
    }

    public ApiResponse<UpdateLanguageResponse> updateLanguage(UpdateLanguageRequest request) {
        try {
            Optional<Language> foundLanguage = languageRepository.findLanguageByCodeOrName(request.getCode(), request.getName());
            if (foundLanguage.isEmpty()) {
                throw new ResourceNotFoundException("Language not found");
            }
            Language existLanguage = foundLanguage.get();
            existLanguage.setName(request.getName());
            languageRepository.save(existLanguage);
            logger.info("Language updated successfully: {}", existLanguage.getName());
            return new ApiResponse<>(true, new UpdateLanguageResponse(existLanguage), "Language updated successfully.");
        }catch (ResourceNotFoundException e){
            logger.error("Language not found", e.getMessage());
            return new ApiResponse<>(false, null, e.getMessage());
        }catch (Exception e) {
            logger.error("Error updating language: {}", e.getMessage());
            return new ApiResponse<>(false, null, "Error creating language.");
        }
    }

    public ApiResponse<DeleteLanguageResponse> deleteTechnology(DeleteLanguageRequest request) {
        try {
            Optional<Language> foundLanguage = languageRepository.findLanguageByCodeOrName(request.getCode(), request.getName());
            if (foundLanguage.isEmpty()) {
                throw new ResourceNotFoundException("Language not found");
            }
            Language existLanguage = foundLanguage.get();
            languageRepository.delete(existLanguage);
            logger.info("Language deleted successfully: {}", existLanguage.getName());
            return new ApiResponse<>(true, new DeleteLanguageResponse(existLanguage), "Language deleted successfully.");
        }catch (ResourceNotFoundException e){
            logger.error("Language not found", request.getName());
            return new ApiResponse<>(false, null, e.getMessage());
        }catch (Exception e) {
            logger.error("Error deleting language: {}", e.getMessage());
            return new ApiResponse<>(false, null, "Error deleting language.");
        }
    }
}
