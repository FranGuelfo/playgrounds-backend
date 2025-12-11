package com.playground.playground.dto;

import lombok.Data;

import java.util.List;

@Data
public class PlaygroundDto {

    private Long id;

    private String name;

    private String address;

    private Double latitude;

    private Double longitude;

    private String description;

    private List<String> photos;

    private Double valorationMedia;


}
