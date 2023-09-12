package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.AddLanguageRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.AddLanguageResponse;
import com.developers.hireasenior.dto.response.ListLanguagesResponse;
import com.developers.hireasenior.service.LanguageService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/language")
@AllArgsConstructor
public class LanguageController {
    private final LanguageService languageService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddLanguageResponse>> addLanguage(@Valid @RequestBody AddLanguageRequest request) {
        return ResponseEntity.ok(languageService.addLanguage(request));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<ListLanguagesResponse>> listLanguages() {
        return ResponseEntity.ok(languageService.listLanguages());
    }
}
