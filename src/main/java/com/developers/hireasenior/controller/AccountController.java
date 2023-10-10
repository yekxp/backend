package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.dto.TechnologyDto;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @GetMapping("/seniorsBasedOnTechnologiesAvailableTime")
    public ResponseEntity<ApiResponse<List<AccountDto>>> filteredSeniorsTechnologyTime(@Valid @RequestBody Set<TechnologyDto> technologiesDto) {

        return ResponseEntity.ok(accountService.findByTechnologies(technologiesDto));
    }
}
