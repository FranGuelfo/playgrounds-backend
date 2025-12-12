package com.playground.playground.mapper;

import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.model.entity.Playground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaygroundMapper {

    @Mapping(target = "photos", ignore = true)
    Playground toEntity(PlaygroundDto dto);

    @Mapping(target = "photos", ignore = true)
    PlaygroundDto toDto(Playground entity);

    List<PlaygroundDto> toDtos(List<Playground> entities);
}

