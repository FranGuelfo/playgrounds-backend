package com.playground.playground.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "playgrounds")
public class Playground {

    @Id
    private String id;

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double valorationMedia = 0.0;
    private LocalDateTime createdAt = LocalDateTime.now();
    private List<PlaygroundPhoto> photos = new ArrayList<>();
}
