package com.playground.playground.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class ReviewDto {

    private Long id;
    private int score;
    private String comment;
    private LocalDateTime date;

    private Long playgroundId;

    private String username;
}

