package com.playground.playground.controller;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/playground/{playgroundId}")
    public List<ReviewDto> getReviewsByPlayground(@PathVariable Long playgroundId) {
        return reviewService.listPlaygroundReviews(playgroundId);
    }

    @PostMapping
    public ReviewDto createReview(@RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable Long id) {
        reviewService.deleteReview(id);
    }
}
