package com.playground.playground.repository;

import com.playground.playground.domain.entity.Review;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReviewRepository extends MongoRepository<Review, String> {
    List<Review> findByPlaygroundId(String playgroundId);
}
