package com.playground.playground.mapper;

import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.model.entity.Playground;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaygroundMapper {

    PlaygroundDto toDto(Playground playground);

    Playground toEntity(PlaygroundDto playgroundDto);

    List<PlaygroundDto> toDtos(List<Playground> playgrounds);
}
