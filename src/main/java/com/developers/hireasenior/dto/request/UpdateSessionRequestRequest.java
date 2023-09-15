package com.developers.hireasenior.dto.request;

import com.developers.hireasenior.model.Session;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateSessionRequestRequest {
    private Session session;
}
