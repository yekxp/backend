package com.developers.hireasenior.service;

import com.developers.hireasenior.dto.request.CreateReviewRequest;
import com.developers.hireasenior.dto.response.ApiResponse;
import com.developers.hireasenior.dto.response.CreateReviewResponse;
import com.developers.hireasenior.dto.response.ListSeniorReviewsResponse;
import com.developers.hireasenior.exception.ResourceNotFoundException;
import com.developers.hireasenior.model.Account;
import com.developers.hireasenior.model.Review;
import com.developers.hireasenior.repository.ReviewRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    public ApiResponse<CreateReviewResponse> createReview(CreateReviewRequest request, Account account) {
        try {
            Review review = new Review();
            review.setText(request.getText());
            review.setJuniorId(account.getId());
            review.setSeniorId(request.getSeniorId());
            review.setDate(LocalDateTime.now());
            review.setRating(request.getRating());
            reviewRepository.save(review);
            return new ApiResponse<CreateReviewResponse>(true, new CreateReviewResponse(review), "Review created successfully.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Review could not be created.");
        }
    }

    public ApiResponse<ListSeniorReviewsResponse> listSeniorReviews(String seniorId) {
        try {
            List<Review> reviews = reviewRepository.findBySeniorId(seniorId);
            return new ApiResponse<ListSeniorReviewsResponse>(true, new ListSeniorReviewsResponse(reviews), "Reviews fetched successfully.");
        } catch (Exception e) {
            return new ApiResponse<>(false, null, "Reviews could not be fetched.");
        }
    }

    public ApiResponse deleteReview(String reviewId, Account account) {
        try {
            Review review = reviewRepository.findById(reviewId).orElseThrow(() -> new ResourceNotFoundException("Review not found."));
            if(!review.getJuniorId().equals(account.getId())) {
                return new ApiResponse(false, "You are not authorized to delete this review.");
            }
            reviewRepository.deleteById(reviewId);
            return new ApiResponse(true, "Review deleted successfully.");
        } catch (Exception e) {
            return new ApiResponse(false, "Review could not be deleted.");
        }
    }

}
