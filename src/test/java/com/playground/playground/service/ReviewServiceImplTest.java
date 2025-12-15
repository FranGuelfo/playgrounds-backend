package com.playground.playground.service;

import com.playground.playground.domain.entity.Review;
import com.playground.playground.exception.ForbiddenException;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.repository.ReviewRepository;
import com.playground.playground.security.user.UserSecurity;
import com.playground.playground.service.impl.ReviewServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReviewServiceImplTest {

    @Mock
    private ReviewRepository reviewRepository;

    @Mock
    private PlaygroundRepository playgroundRepository;

    @InjectMocks
    private ReviewServiceImpl reviewService;

    @Test
    void shouldThrowForbiddenException_whenUserIsNotAuthorNorAdmin() {
        // given
        Review review = new Review();
        UserSecurity author = new UserSecurity();
        author.setId(1L);
        review.setUser(author);

        when(reviewRepository.findById(10L)).thenReturn(Optional.of(review));

        // when / then
        assertThrows(ForbiddenException.class,
                () -> reviewService.deleteReview(10L));
    }
}