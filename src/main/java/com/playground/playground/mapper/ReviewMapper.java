package com.playground.playground.mapper;

import com.playground.playground.dto.ReviewDto;
import com.playground.playground.domain.entity.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "playgroundId", target = "playgroundId")
    @Mapping(source = "userId", target = "userId")
    ReviewDto toReviewDto(Review review);

    List<ReviewDto> toReviewDtos(List<Review> reviews);
}

