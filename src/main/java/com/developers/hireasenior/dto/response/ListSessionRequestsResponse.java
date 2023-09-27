package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.SessionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ListSessionRequestsResponse {
    private List<SessionRequest> outgoingSessionRequests;
    private List<SessionRequest> incomingSessionRequests;
}
