package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateSessionRequestResponse;
import com.developers.hireasenior.service.SessionRequestService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/session-request")
public class SessionRequestController {
    private final SessionRequestService sessionRequestService;

    public SessionRequestController(SessionRequestService sessionRequestService) {
        this.sessionRequestService = sessionRequestService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateSessionRequestResponse>> createSessionRequest(@Valid @RequestBody CreateSessionRequestRequest request) {
        return ResponseEntity.ok(sessionRequestService.createSessionRequest(request));
    }
}
