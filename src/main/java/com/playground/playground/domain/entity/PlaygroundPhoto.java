package com.playground.playground.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class PlaygroundPhoto {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String url;

    @ManyToOne
    @JoinColumn(name = "playground_id")
    private Playground playground;
}

