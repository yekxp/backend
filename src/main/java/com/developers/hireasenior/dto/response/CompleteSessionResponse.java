package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompleteSessionResponse {
    private Session session;
}
