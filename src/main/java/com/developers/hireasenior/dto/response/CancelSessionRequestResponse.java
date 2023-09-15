package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Session;
import com.developers.hireasenior.model.SessionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CancelSessionRequestResponse {
    private SessionRequest sessionRequest;
}
