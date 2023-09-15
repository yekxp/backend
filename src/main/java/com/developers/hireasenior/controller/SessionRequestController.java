package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.AcceptSessionRequest;
import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.request.UpdateSessionRequestRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.service.SessionRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/session-request")
@AllArgsConstructor
public class SessionRequestController {
    private final SessionRequestService sessionRequestService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateSessionRequestResponse>> createSessionRequest(@Valid @RequestBody CreateSessionRequestRequest request) {
        return ResponseEntity.ok(sessionRequestService.createSessionRequest(request));
    }

    @PostMapping("/accept")
    public ResponseEntity<ApiResponse<AcceptSessionResponse>> acceptSessionRequest(@Valid @RequestBody AcceptSessionRequest request) {
        return ResponseEntity.ok(sessionRequestService.acceptSessionRequest(request.getSession().getId()));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UpdateSessionRequestResponse>> updateSessionRequest(@PathVariable String id, @Valid @RequestBody UpdateSessionRequestRequest request) {
        return ResponseEntity.ok(sessionRequestService.updateSessionRequest(id, request));
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<CancelSessionRequestResponse>> cancelSessionRequest(@PathVariable String id) {
        return ResponseEntity.ok(sessionRequestService.cancelSessionRequest(id));
    }
}
