package com.playground.playground.service.impl;

import com.playground.playground.config.security.SecurityUtils;
import com.playground.playground.dto.ReviewDto;
import com.playground.playground.exception.ForbiddenException;
import com.playground.playground.mapper.ReviewMapper;
import com.playground.playground.model.Role;
import com.playground.playground.model.entity.Playground;
import com.playground.playground.model.entity.Review;
import com.playground.playground.model.security.UserSecurity;
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
                .orElseThrow(() -> new RuntimeException("Playground not found"));

        UserSecurity user = SecurityUtils.getCurrentUser();

        Review review = new Review();
        review.setScore(reviewDto.getScore());
        review.setComment(reviewDto.getComment());
        review.setDate(LocalDateTime.now());
        review.setPlayground(playground);
        review.setUser(user);

        Review saved = reviewRepository.save(review);

        updateValorationMedia(playground);

        return reviewMapper.toReviewDto(saved);
    }

    public void deleteReview(Long id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        UserSecurity currentUser = SecurityUtils.getCurrentUser();

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = review.getUser().getId().equals(currentUser.getId());

        if (!isAuthor && !isAdmin) {
            throw new ForbiddenException("You can only delete your own reviews");
        }

        Playground playground = review.getPlayground();
        reviewRepository.delete(review);
        updateValorationMedia(playground);
    }

    @Override
    public ReviewDto updateReview(Long id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        UserSecurity currentUser = SecurityUtils.getCurrentUser();

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = review.getUser().getId().equals(currentUser.getId());

        if (!isAuthor && !isAdmin) {
            throw new ForbiddenException("You can only edit your own reviews");
        }

        // Actualizamos campos permitidos
        review.setComment(reviewDto.getComment());
        review.setScore(reviewDto.getScore());
        review.setDate(LocalDateTime.now()); // opcional: actualizar fecha de edición

        Review saved = reviewRepository.save(review);

        // Recalcular valoración media si cambió el score
        updateValorationMedia(review.getPlayground());

        return reviewMapper.toReviewDto(saved);
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
