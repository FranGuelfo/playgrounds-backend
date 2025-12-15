package com.playground.playground.repository;

import com.playground.playground.domain.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByPlaygroundId(Long playgroundId);
}
