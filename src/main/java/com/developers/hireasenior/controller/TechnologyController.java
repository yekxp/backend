package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.AddTechnologyRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.AddTechnologyResponse;
import com.developers.hireasenior.dto.response.ListTechnologiesResponse;
import com.developers.hireasenior.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/technology")
@AllArgsConstructor
public class TechnologyController {
    private final TechnologyService technologyService;

    @PostMapping("/add")
    public ResponseEntity<ApiResponse<AddTechnologyResponse>> addTechnology(@Valid @RequestBody AddTechnologyRequest request) {
        return ResponseEntity.ok(technologyService.addTechnology(request));
    }

    @GetMapping("/list")
    public ResponseEntity<ApiResponse<ListTechnologiesResponse>> listTechnologies() {
        return ResponseEntity.ok(technologyService.listTechnologies());
    }
}
