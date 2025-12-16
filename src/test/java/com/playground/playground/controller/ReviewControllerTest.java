package com.playground.playground.controller;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.service.ReviewService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.mockito.Mockito.*;

class ReviewControllerTest {
    @Mock
    ReviewService reviewService;
    @InjectMocks
    ReviewController reviewController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetReviewsByPlayground() {
        when(reviewService.listPlaygroundReviews(anyString())).thenReturn(List.of(new ReviewDto("id", 0, "comment",
                LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42, 9), "playgroundId", "userId")));

        List<ReviewDto> result = reviewController.getReviewsByPlayground("playgroundId");
        Assertions.assertEquals(List.of(new ReviewDto("id", 0, "comment", LocalDateTime.of(2025, Month.DECEMBER, 16,
                10, 42, 9), "playgroundId", "userId")), result);
    }

    @Test
    void testCreateReview() {
        when(reviewService.createReview(any(ReviewDto.class))).thenReturn(new ReviewDto("id", 0, "comment",
                LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42, 9), "playgroundId", "userId"));

        ReviewDto result = reviewController.createReview(new ReviewDto("id", 0, "comment", LocalDateTime.of(2025,
                Month.DECEMBER, 16, 10, 42, 9), "playgroundId", "userId"));
        Assertions.assertEquals(new ReviewDto("id", 0, "comment", LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42,
                9), "playgroundId", "userId"), result);
    }

    @Test
    void testUpdateReview() {
        when(reviewService.updateReview(anyString(), any(ReviewDto.class))).thenReturn(new ReviewDto("id", 0,
                "comment", LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42, 9), "playgroundId", "userId"));

        ReviewDto result = reviewController.updateReview("id", new ReviewDto("id", 0, "comment",
                LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42, 9), "playgroundId", "userId"));
        Assertions.assertEquals(new ReviewDto("id", 0, "comment", LocalDateTime.of(2025, Month.DECEMBER, 16, 10, 42,
                9), "playgroundId", "userId"), result);
    }

    @Test
    void testDeleteReview() {
        reviewController.deleteReview("id");
        verify(reviewService).deleteReview(anyString());
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme