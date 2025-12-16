package com.playground.playground.service.impl;

import com.playground.playground.exception.PlaygroundNotFoundException;
import com.playground.playground.security.SecurityUtils;
import com.playground.playground.dto.ReviewDto;
import com.playground.playground.exception.ForbiddenException;
import com.playground.playground.exception.ReviewNotFoundException;
import com.playground.playground.mapper.ReviewMapper;
import com.playground.playground.domain.enums.Role;
import com.playground.playground.domain.entity.Playground;
import com.playground.playground.domain.entity.Review;
import com.playground.playground.security.user.UserSecurity;
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
    public List<ReviewDto> listPlaygroundReviews(String playgroundId) {
        List<Review> reviews = reviewRepository.findByPlaygroundId(playgroundId);
        return reviewMapper.toReviewDtos(reviews);
    }

    @Override
    public ReviewDto createReview(ReviewDto reviewDto) {

        Playground playground = playgroundRepository.findById(reviewDto.getPlaygroundId())
                .orElseThrow(ReviewNotFoundException::new);

        UserSecurity user = SecurityUtils.getCurrentUser();

        Review review = new Review();
        review.setScore(reviewDto.getScore());
        review.setComment(reviewDto.getComment());
        review.setDate(LocalDateTime.now());
        review.setPlaygroundId(playground.getId());
        review.setUserId(user.getId());

        Review saved = reviewRepository.save(review);

        updateValorationMedia(playground);

        return reviewMapper.toReviewDto(saved);
    }

    @Override
    public void deleteReview(String id) {

        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);

        UserSecurity currentUser = SecurityUtils.getCurrentUser();

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = review.getUserId().equals(currentUser.getId());

        if (!isAuthor && !isAdmin) {
            throw new ForbiddenException("You can only delete your own reviews");
        }

        Playground playground = playgroundRepository.findById(review.getPlaygroundId())
                .orElseThrow(PlaygroundNotFoundException::new);

        reviewRepository.delete(review);
        updateValorationMedia(playground);
    }

    @Override
    public ReviewDto updateReview(String id, ReviewDto reviewDto) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(ReviewNotFoundException::new);

        UserSecurity currentUser = SecurityUtils.getCurrentUser();

        boolean isAdmin = currentUser.getRole() == Role.ADMIN;
        boolean isAuthor = review.getUserId().equals(currentUser.getId());

        if (!isAuthor && !isAdmin) {
            throw new ForbiddenException("You can only edit your own reviews");
        }

        review.setComment(reviewDto.getComment());
        review.setScore(reviewDto.getScore());
        review.setDate(LocalDateTime.now());

        Review saved = reviewRepository.save(review);

        Playground playground = playgroundRepository.findById(review.getPlaygroundId())
                .orElseThrow(PlaygroundNotFoundException::new);

        updateValorationMedia(playground);

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
