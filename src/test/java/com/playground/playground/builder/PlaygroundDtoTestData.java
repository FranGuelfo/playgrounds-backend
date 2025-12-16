package com.playground.playground.builder;


import com.playground.playground.dto.PlaygroundDto;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class PlaygroundDtoTestData {

    private String name;
    private String address;
    private Double latitude;
    private Double longitude;
    private String description;

    public PlaygroundDto toDto() {
        PlaygroundDto dto = new PlaygroundDto();
        dto.setName(name);
        dto.setAddress(address);
        dto.setLatitude(latitude);
        dto.setLongitude(longitude);
        dto.setDescription(description);
        return dto;
    }
}