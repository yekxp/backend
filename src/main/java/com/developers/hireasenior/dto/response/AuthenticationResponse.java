package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthenticationResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";
    private LocalDateTime accessExpiresAt;
    private LocalDateTime refreshExpiresAt;
    private boolean isAdmin;
    private String firstName;
    private String email;
    private Role role;
}
