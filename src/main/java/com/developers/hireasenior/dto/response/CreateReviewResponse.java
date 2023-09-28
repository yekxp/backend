package com.developers.hireasenior.dto.response;

import com.developers.hireasenior.model.Review;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateReviewResponse {
    private Review review;
}
