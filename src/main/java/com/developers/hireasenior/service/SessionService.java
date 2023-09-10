package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CancelSessionResponse;
import com.developers.hireasenior.dto.response.CompleteSessionResponse;
import com.developers.hireasenior.repository.SessionRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionService {
    private final SessionRepository sessionRepository;

    public ApiResponse<CancelSessionResponse> cancelSession(String id) {
        return null;
    }

    public ApiResponse<CompleteSessionResponse> completeSession(String id) {
        return null;
    }
}
