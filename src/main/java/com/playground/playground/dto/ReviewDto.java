package com.playground.playground.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDto {

    private String id;
    private int score;
    private String comment;
    private LocalDateTime date;

    private String playgroundId;

    private String userId;
}

