package com.playground.playground.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class PlaygroundDto {

    private Long id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;

    // En el DTO solo mantenemos las URLs
    private List<String> photos = new ArrayList<>();

    private Double valorationMedia;
    private LocalDateTime createdAt;
}
