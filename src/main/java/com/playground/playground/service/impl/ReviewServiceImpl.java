package com.playground.playground.service.impl;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.mapper.ReviewMapper;
import com.playground.playground.model.entity.Playground;
import com.playground.playground.model.entity.Review;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.repository.ReviewRepository;
import com.playground.playground.service.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    private final PlaygroundRepository playgroundRepository;

    private final ReviewMapper reviewMapper;

    @Override
    public List<ReviewDto> listPlaygroundReviews(Long playgroundId) {
        List<Review> reviews = reviewRepository.findByPlaygroundId(playgroundId);
        return reviewMapper.toReviewDtos(reviews);
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {
        Playground playground = playgroundRepository.findById(reviewDto.getPlaygroundId())
                .orElseThrow(() -> new RuntimeException("playground not found"));

        Review review = reviewMapper.toReviewEntity(reviewDto);
        review.setPlayground(playground);
        review.setDate(LocalDateTime.now());

        Review reviewSaved = reviewRepository.save(review);

        updateValorationMedia(playground);

        return reviewMapper.toReviewDto(reviewSaved);
    }

    @Override
    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    private void updateValorationMedia(Playground playground) {
        List<Review> reviews = reviewRepository.findByPlaygroundId(playground.getId());

        double media = reviews.stream()
                .mapToInt(Review::getScore)
                .average()
                .orElse(0);

        playground.setValorationMedia(media);
        playgroundRepository.save(playground);
    }
}
