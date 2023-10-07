package com.developers.hireasenior.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewRequest {
    private String text;
    private String seniorId;
    private String rating;
}
