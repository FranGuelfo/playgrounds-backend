package com.playground.playground.model.entity;

import com.playground.playground.model.security.UserSecurity;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int score;

    private String comment;

    private LocalDateTime date;

    @ManyToOne(optional = false)
    @JoinColumn(name = "playground_id")
    private Playground playground;

    @ManyToOne(optional = false)
    @JoinColumn(name = "user_id")
    private UserSecurity user;
}
