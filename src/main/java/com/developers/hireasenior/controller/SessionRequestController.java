package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.AcceptSessionRequest;
import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.request.UpdateSessionRequestRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.exception.TitleNotAllowedException;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.Title;
import com.developers.hireasenior.service.AuthService;
import com.developers.hireasenior.service.JwtService;
import com.developers.hireasenior.service.SessionRequestService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/api/v1/session-request")
@AllArgsConstructor
public class SessionRequestController {
    private final SessionRequestService sessionRequestService;
    private final AuthService authService;
    private final JwtService jwtService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<CreateSessionRequestResponse>> createSessionRequest
            (@Valid @RequestBody CreateSessionRequestRequest request, @RequestHeader (name="Authorization") String token) {
        try {
            // Validates that the token owner has the title of junior, otherwise throws exception.
            authService.doesTokenHaveTitleOf(token, Title.JUNIOR);

            return ResponseEntity.ok(sessionRequestService.createSessionRequest(request));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Account not found."));
        } catch (TitleNotAllowedException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Only juniors can create session requests."));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "An unknown error occurred when creating session request."));
        }
    }

    @PostMapping("/accept")
    public ResponseEntity<ApiResponse<AcceptSessionResponse>> acceptSessionRequest
            (@Valid @RequestBody AcceptSessionRequest request, @RequestHeader (name="Authorization") String token) {
        try {
            // Validates that the token owner has the title of junior, otherwise throws exception.
            Account account = authService.doesTokenHaveTitleOf(token, Title.SENIOR);

            return ResponseEntity.ok(sessionRequestService.acceptSessionRequest(request.getSession().getId(), account));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Account not found."));
        } catch (TitleNotAllowedException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Only juniors can create session requests."));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "An unknown error occurred when creating session request."));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<UpdateSessionRequestResponse>> updateSessionRequest(@PathVariable String id,
                                                                                          @Valid @RequestBody UpdateSessionRequestRequest request,
                                                                                          @RequestHeader (name="Authorization") String token) {
        try {
            Account account = authService.doesTokenHaveTitleOf(token, Title.JUNIOR);
            return ResponseEntity.ok(sessionRequestService.updateSessionRequest(id, request, account));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Account not found."));
        } catch (TitleNotAllowedException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "Only juniors can create session requests."));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "An unknown error occurred when creating session request."));
        }
    }

    @GetMapping("/cancel/{id}")
    public ResponseEntity<ApiResponse<CancelSessionRequestResponse>> cancelSessionRequest(@PathVariable String id) {
        return ResponseEntity.ok(sessionRequestService.cancelSessionRequest(id));
    }

    /*
        List all session requests of the user.
     */
    @GetMapping("/list")
    public ResponseEntity<ApiResponse<ListSessionRequestsResponse>> listSessionRequests(@RequestHeader (name="Authorization") String token) {
        try {
            Account account = authService.extractAccountFromToken(token.replace("Bearer ", ""));
            return ResponseEntity.ok(sessionRequestService.listSessionRequests(account));
        } catch (AccountNotFoundException e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "You are not authorized."));
        } catch (Exception e) {
            return ResponseEntity.ok(new ApiResponse<>(false, null, "An unknown error occurred when creating session request."));
        }
    }
}
