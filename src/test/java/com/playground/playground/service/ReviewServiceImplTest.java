package com.playground.playground.service;

import com.playground.playground.domain.entity.Playground;
import com.playground.playground.domain.entity.Review;
import com.playground.playground.domain.enums.Role;
import com.playground.playground.dto.ReviewDto;
import com.playground.playground.exception.ForbiddenException;
import com.playground.playground.mapper.ReviewMapper;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.repository.ReviewRepository;
import com.playground.playground.security.SecurityUtils;
import com.playground.playground.security.user.UserSecurity;
import com.playground.playground.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PlaygroundRepository playgroundRepository;

    @Mock
    private ReviewMapper reviewMapper;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void createReview_shouldCreateReviewAndUpdateValoration() {
        // given
        ReviewDto dto = new ReviewDto();
        dto.setPlaygroundId("1");
        dto.setScore(4);
        dto.setComment("Nice playground");

        Playground playground = new Playground();
        playground.setId("1");

        UserSecurity user = new UserSecurity();
        user.setId("user1");
        user.setRole(Role.USER);

        when(playgroundRepository.findById("1"))
                .thenReturn(Optional.of(playground));

        when(reviewRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        when(reviewRepository.findByPlaygroundId("1"))
                .thenReturn(List.of());

        when(reviewMapper.toReviewDto(any()))
                .thenReturn(new ReviewDto());

        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUser).thenReturn(user);

            // when
            ReviewDto result = reviewService.createReview(dto);

            // then
            assertNotNull(result);
            verify(reviewRepository).save(any(Review.class));
            verify(playgroundRepository).save(any(Playground.class));
        }
    }

    @Test
    void deleteReview_shouldThrowForbidden_whenNotOwnerOrAdmin() {
        // given
        Review review = new Review();
        UserSecurity author = new UserSecurity();
        author.setId("author");

        UserSecurity currentUser = new UserSecurity();
        currentUser.setId("other");
        currentUser.setRole(Role.USER);

        review.setUserId("1");

        when(reviewRepository.findById("1"))
                .thenReturn(Optional.of(review));

        try (MockedStatic<SecurityUtils> mockedSecurity = mockStatic(SecurityUtils.class)) {
            mockedSecurity.when(SecurityUtils::getCurrentUser).thenReturn(currentUser);

            // then
            assertThrows(
                    ForbiddenException.class,
                    () -> reviewService.deleteReview("1")
            );
        }
    }
}