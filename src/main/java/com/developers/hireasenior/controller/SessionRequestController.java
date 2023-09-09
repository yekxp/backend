package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.AcceptSessionRequest;
import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.response.AcceptSessionResponse;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateSessionRequestResponse;
import com.developers.hireasenior.dto.response.CreateSessionResponse;
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

    @PostMapping("/accept")
    public ResponseEntity<ApiResponse<AcceptSessionResponse>> acceptSessionRequest(@Valid @RequestBody AcceptSessionRequest request) {
        return ResponseEntity.ok(sessionRequestService.acceptSessionRequest(request.getSession().getId()));
    }
}
