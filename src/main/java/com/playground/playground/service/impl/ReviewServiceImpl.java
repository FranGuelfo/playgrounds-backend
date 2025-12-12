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

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        // Buscar la review existente
        Review existingReview = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        // Actualizar campos
        existingReview.setUsername(reviewDto.getUsername());
        existingReview.setScore(reviewDto.getScore());
        existingReview.setComment(reviewDto.getComment());
        existingReview.setDate(LocalDateTime.now());

        // Si el playground cambia, actualizar la relación
        if (reviewDto.getPlaygroundId() != null &&
                !reviewDto.getPlaygroundId().equals(existingReview.getPlayground().getId())) {

            Playground playground = playgroundRepository.findById(reviewDto.getPlaygroundId())
                    .orElseThrow(() -> new RuntimeException("Playground not found"));

            existingReview.setPlayground(playground);
        }

        // Guardar la review actualizada
        Review updatedReview = reviewRepository.save(existingReview);

        // Actualizar valoración media del playground
        updateValorationMedia(existingReview.getPlayground());

        // Devolver DTO
        return reviewMapper.toReviewDto(updatedReview);
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
