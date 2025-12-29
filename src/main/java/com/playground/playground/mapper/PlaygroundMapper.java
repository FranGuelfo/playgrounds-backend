package com.playground.playground.mapper;

import com.playground.playground.domain.entity.PlaygroundPhoto;
import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.domain.entity.Playground;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PlaygroundMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "valorationMedia", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Playground toEntity(CreatePlaygroundDto  dto);

    @Mapping(target = "photos", source = "photos")
    PlaygroundDto toDto(Playground entity);

    default List<PlaygroundPhoto> mapPhotos(List<String> photos) {
        if (photos == null) return List.of();

        return photos.stream()
                .map(url -> {
                    PlaygroundPhoto photo = new PlaygroundPhoto();
                    photo.setUrl(url);
                    return photo;
                })
                .toList();
    }

    default List<String> mapPhotoEntities(List<PlaygroundPhoto> photos) {
        if (photos == null) return List.of();

        return photos.stream()
                .map(PlaygroundPhoto::getUrl)
                .toList();
    }
}

