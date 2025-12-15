package com.playground.playground.domain.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Playground {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double valorationMedia;
    private LocalDateTime createdAt;

    @OneToMany(mappedBy = "playground", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PlaygroundPhoto> photos = new ArrayList<>();
}
