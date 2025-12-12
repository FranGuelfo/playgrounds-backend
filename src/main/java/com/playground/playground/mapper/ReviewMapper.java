package com.playground.playground.mapper;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.model.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "playground.id", target = "playgroundId")
    ReviewDto toReviewDto(Review review);

    @Mapping(source = "playgroundId", target = "playground.id")
    Review toReviewEntity(ReviewDto reviewDto);

    List<ReviewDto> toReviewDtos(List<Review> reviews);
}
