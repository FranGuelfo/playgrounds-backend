package com.playground.playground.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaygroundDto {

    private String id;
    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;
    private Double valorationMedia;
    private List<String> photos = new ArrayList<>();
}
