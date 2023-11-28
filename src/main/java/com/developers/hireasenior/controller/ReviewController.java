package com.developers.hireasenior.controller;

import com.developers.hireasenior.dto.request.CreateReviewRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateReviewResponse;
import com.developers.hireasenior.dto.response.ListSeniorReviewsResponse;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.service.AuthService;
import com.developers.hireasenior.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {
    private final ReviewService reviewService;
    private final AuthService authService;

    @PostMapping
    public ApiResponse<CreateReviewResponse> createReview(CreateReviewRequest request, @RequestHeader (name="Authorization") String token) {
        try {
            Account account = authService.extractAccountFromToken(token.replace("Bearer ", ""));
            return reviewService.createReview(request, account);
        } catch (AccountNotFoundException e) {
            return new ApiResponse<>(false, null, "Account not found.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Review could not be created.");
        }
    }

    @GetMapping("/{seniorId}")
    public ApiResponse<ListSeniorReviewsResponse> listSeniorReviews(@PathVariable String seniorId) {
        try {
            return reviewService.listSeniorReviews(seniorId);
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Reviews could not be fetched.");
        }
    }

    @DeleteMapping("/{reviewId}")
    public ApiResponse deleteReview(@PathVariable String reviewId, @RequestHeader (name="Authorization") String token) {
        try {
            Account account = authService.extractAccountFromToken(token.replace("Bearer ", ""));
            return reviewService.deleteReview(reviewId, account);
        } catch (AccountNotFoundException e) {
            return new ApiResponse<>(false, null, "Account not found.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Review could not be deleted.");
        }
    }
}
