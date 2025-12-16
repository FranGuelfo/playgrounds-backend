package com.playground.playground.service;

import com.playground.playground.dto.ReviewDto;

import java.util.List;

public interface ReviewService {

    List<ReviewDto> listPlaygroundReviews(String playgroundId);

    ReviewDto createReview(ReviewDto reviewDto);

    void deleteReview(String id);

    ReviewDto updateReview(String id, ReviewDto reviewDto);
}
