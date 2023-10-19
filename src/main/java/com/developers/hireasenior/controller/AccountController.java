package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.AccountDto;
import com.developers.hireasenior.dto.request.TechnologyAndPeriodRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.service.AccountService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/account")
@AllArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/developers-by-technologies-and-available-time")
    public ResponseEntity<ApiResponse<List<AccountDto>>> filteredSeniorsTechnologyTime(@Valid @RequestBody TechnologyAndPeriodRequest technologyAndPeriodRequest) {
        ApiResponse<List<AccountDto>> apiAccountsByTechnologies = accountService.findByTechnologies(technologyAndPeriodRequest.getTechnologyDtoSet());

        if(NotFound(apiAccountsByTechnologies))
            return ResponseEntity.ok(apiAccountsByTechnologies);

        ApiResponse<List<AccountDto>> apiAccountsAvailableTime = accountService.findByPeriodTime(technologyAndPeriodRequest.getStartedAt(), technologyAndPeriodRequest.getEndedAt());

        if(NotFound(apiAccountsAvailableTime))
            return ResponseEntity.ok(apiAccountsByTechnologies);

        List<AccountDto> result = apiAccountsByTechnologies.getData().stream()
                .distinct()
                .filter(apiAccountsAvailableTime.getData()::contains)
                .collect(Collectors.toList());

        return  ResponseEntity.ok(new ApiResponse<>(true, result, "Successfully found developers."));
    }

    private boolean NotFound(ApiResponse<List<AccountDto>> apiResponse){
        if(!apiResponse.isSuccess() || apiResponse.getData() == null)
            return true;

        return false;
    }

}
