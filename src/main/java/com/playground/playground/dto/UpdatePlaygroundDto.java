package com.playground.playground.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdatePlaygroundDto {

    @NotBlank
    @Size(max = 100)
    private String name;

    @NotBlank
    private String address;

    private Double latitude;
    private Double longitude;

    @Size(max = 500)
    private String description;

    private List<@NotBlank String> photos;
}