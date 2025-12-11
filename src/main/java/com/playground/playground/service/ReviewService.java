package com.playground.playground.service;

import com.playground.playground.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> listPlaygroundReviews(Long playgroundId);

    ReviewDto createReview(ReviewDto reviewDto);
    void deleteReview(Long id);
}
