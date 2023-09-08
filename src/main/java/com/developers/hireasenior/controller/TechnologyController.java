package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.CreateTechnologyRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateTechnologyResponse;
import com.developers.hireasenior.service.TechnologyService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/technology")
public class TechnologyController {
    private final TechnologyService technologyService;

    public TechnologyController(TechnologyService technologyService) {
        this.technologyService = technologyService;
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<CreateTechnologyResponse>> addTechnology(@Valid @RequestBody CreateTechnologyRequest request) {
        return ResponseEntity.ok(technologyService.addTechnology(request));
    }
}
