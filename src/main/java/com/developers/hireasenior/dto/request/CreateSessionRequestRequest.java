package com.developers.hireasenior.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateSessionRequestRequest {
    private String juniorId;
    private String seniorId;
    private Double hourlyPrice;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
