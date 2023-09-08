package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateSessionRequestResponse;
import com.developers.hireasenior.exception.ResourceNotFoundException;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.SessionRequest;
import com.developers.hireasenior.model.SessionStatus;
import com.developers.hireasenior.repository.AccountRepository;
import com.developers.hireasenior.repository.SessionRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class SessionRequestService {
    private final SessionRequestRepository sessionRequestRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(SessionRequestService.class);

    public SessionRequestService(SessionRequestRepository sessionRequestRepository, AccountRepository accountRepository) {
        this.sessionRequestRepository = sessionRequestRepository;
        this.accountRepository = accountRepository;
    }

    public ApiResponse<CreateSessionRequestResponse> createSessionRequest(CreateSessionRequestRequest request) {
        try {
            Account junior = accountRepository.findById(request.getJuniorId()).orElseThrow(() -> new ResourceNotFoundException("account"));
            Account senior = accountRepository.findById(request.getSeniorId()).orElseThrow(() -> new ResourceNotFoundException("account"));
            // Add payment logic here
            SessionRequest sessionRequest = new SessionRequest();
            // Get the junior from the token
            sessionRequest.setJunior(junior);
            sessionRequest.setSenior(senior);
            sessionRequest.setStartDate(request.getStartDate());
            sessionRequest.setEndDate(request.getEndDate());
            sessionRequest.setHourlyPrice(request.getHourlyPrice());
            sessionRequest.setStatus(SessionStatus.PENDING);

            sessionRequestRepository.save(sessionRequest);
            logger.info("Session request created successfully: {}", sessionRequest.getJunior().getEmail());
            return new ApiResponse<>(true, new CreateSessionRequestResponse(sessionRequest), "Session request created successfully.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "An unknown error occurred when creating session request.");
        }
    }

}
