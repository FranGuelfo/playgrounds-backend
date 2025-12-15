package com.playground.playground.controller;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.service.ReviewService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/playground/{playgroundId}")
    public List<ReviewDto> getReviewsByPlayground(@PathVariable String playgroundId) {
        return reviewService.listPlaygroundReviews(playgroundId);
    }

    @PostMapping
    public ReviewDto createReview(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.createReview(reviewDto);
    }

    @PutMapping("/{id}")
    public ReviewDto updateReview(@PathVariable String id, @RequestBody ReviewDto reviewDto){
        return reviewService.updateReview(id, reviewDto);
    }

    @DeleteMapping("/{id}")
    public void deleteReview(@PathVariable String id) {
        reviewService.deleteReview(id);
    }
}
