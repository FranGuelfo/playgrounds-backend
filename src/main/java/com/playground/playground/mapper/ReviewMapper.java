package com.playground.playground.mapper;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "playground.id", target = "playgroundId")
    @Mapping(source = "user.username", target = "username")
    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDtos(List<Review> reviews);
}

