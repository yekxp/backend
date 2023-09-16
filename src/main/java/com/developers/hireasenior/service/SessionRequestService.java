package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.CreateSessionRequestRequest;
import com.developers.hireasenior.dto.request.UpdateSessionRequestRequest;
import com.developers.hireasenior.dto.response.*;
import com.developers.hireasenior.exception.ResourceNotFoundException;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.SessionRequest;
import com.developers.hireasenior.model.SessionStatus;
import com.developers.hireasenior.repository.AccountRepository;
import com.developers.hireasenior.repository.SessionRequestRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class SessionRequestService {
    private final SessionRequestRepository sessionRequestRepository;
    private final AccountRepository accountRepository;
    private static final Logger logger = LoggerFactory.getLogger(SessionRequestService.class);

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

    public ApiResponse<AcceptSessionResponse> acceptSessionRequest(String sessionId, Account account) {
        try {
            SessionRequest sessionRequest = sessionRequestRepository.findById(sessionId).orElseThrow(() -> new ResourceNotFoundException("session request"));
            if(!sessionRequest.getSenior().getId().equals(account.getId())){
                return new ApiResponse<>(false, null, "Only the requested senior can accept the session request.");
            }
            sessionRequest.setStatus(SessionStatus.ACCEPTED);
            sessionRequestRepository.save(sessionRequest);

            // TODO: Send email to junior
            logger.info("Session request accepted successfully: {}", sessionRequest.getSenior().getEmail());
            return new ApiResponse<>(true, new AcceptSessionResponse(sessionRequest), "Session request accepted successfully.");
        } catch (ResourceNotFoundException e) {
            return new ApiResponse<>(false, null, "Session request not found.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "An unknown error occurred when accepting session request.");
        }
    }

    public ApiResponse<UpdateSessionRequestResponse> updateSessionRequest(String id,
                                                                          UpdateSessionRequestRequest request,
                                                                          Account account) {
        try {
            SessionRequest sessionRequest = sessionRequestRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("session request"));

            if(!sessionRequest.getJunior().getId().equals(account.getId())){
                return new ApiResponse<>(false, null, "Only the requested junior can update the session request.");
            }
            // Session is updated, so status is set to pending again.
            sessionRequest.setStatus(SessionStatus.PENDING);
            sessionRequest.setHourlyPrice(request.getSessionRequest().getHourlyPrice());
            sessionRequest.setStartDate(request.getSessionRequest().getStartDate());
            sessionRequest.setEndDate(request.getSessionRequest().getEndDate());
            sessionRequestRepository.save(sessionRequest);

            // TODO: Send email to senior
            logger.info("Session request updated successfully: {}", sessionRequest.getJunior().getEmail());
            return new ApiResponse<>(true, new UpdateSessionRequestResponse(sessionRequest), "Session request updated successfully.");
        } catch (ResourceNotFoundException e) {
            return new ApiResponse<>(false, null, "Session request not found.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "An unknown error occurred when updating session request.");
        }
    }

    public ApiResponse<CancelSessionRequestResponse> cancelSessionRequest(String id) {
        try {
            SessionRequest sessionRequest = sessionRequestRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("session request"));
            sessionRequest.setStatus(SessionStatus.CANCELLED);
            sessionRequestRepository.save(sessionRequest);
            // TODO: Send email to senior
            return new ApiResponse<>(true, new CancelSessionRequestResponse(sessionRequest), "Session request cancelled successfully.");
        } catch (ResourceNotFoundException e) {
            return new ApiResponse<>(false, null, "Session request not found.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "An unknown error occurred when cancelling session request.");
        }
    }
}
