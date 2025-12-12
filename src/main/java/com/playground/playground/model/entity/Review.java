package com.playground.playground.model.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;

    private int score;

    private String comment;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "playground_id")
    private Playground playground;
}
