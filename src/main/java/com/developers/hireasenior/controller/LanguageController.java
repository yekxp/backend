package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.*;
import com.developers.hireasenior.dto.response.*;
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

    @PutMapping("/update")
    public ResponseEntity<ApiResponse<UpdateLanguageResponse>> updateLanguage(@Valid @RequestBody UpdateLanguageRequest request){
        return ResponseEntity.ok(languageService.updateLanguage(request));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<ApiResponse<DeleteLanguageResponse>> deleteTechnology(@Valid @RequestBody DeleteLanguageRequest request){
        return ResponseEntity.ok(languageService.deleteTechnology(request));
    }
}
