package com.playground.playground.service.impl;

import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.mapper.PlaygroundMapper;
import com.playground.playground.model.entity.Playground;
import com.playground.playground.model.entity.PlaygroundPhoto;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundServiceImpl implements PlaygroundService {

    private final PlaygroundRepository repository;
    private final PlaygroundMapper mapper;

    @Override
    public PlaygroundDto createPlayground(PlaygroundDto dto) {
        Playground playground = mapper.toEntity(dto);
        playground.setValorationMedia(0.0);
        playground.setCreatedAt(LocalDateTime.now());

        // Mapear manualmente fotos
        List<PlaygroundPhoto> photos = dto.getPhotos().stream().map(url -> {
            PlaygroundPhoto photo = new PlaygroundPhoto();
            photo.setUrl(url);
            photo.setPlayground(playground);
            return photo;
        }).toList();
        playground.setPhotos(photos);

        Playground saved = repository.save(playground);
        return toDtoWithPhotos(saved);
    }

    @Override
    public PlaygroundDto updatePlayground(Long id, PlaygroundDto dto) {
        Playground playground = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playground not found"));

        playground.setName(dto.getName());
        playground.setAddress(dto.getAddress());
        playground.setLatitude(dto.getLatitude());
        playground.setLongitude(dto.getLongitude());
        playground.setDescription(dto.getDescription());

        // Limpiar fotos antiguas y agregar nuevas
        playground.getPhotos().clear();
        List<PlaygroundPhoto> photos = dto.getPhotos().stream().map(url -> {
            PlaygroundPhoto photo = new PlaygroundPhoto();
            photo.setUrl(url);
            photo.setPlayground(playground);
            return photo;
        }).toList();
        playground.getPhotos().addAll(photos);

        Playground saved = repository.save(playground);
        return toDtoWithPhotos(saved);
    }

    @Override
    public PlaygroundDto getPlaygrounds(Long id) {
        Playground playground = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playground not found"));
        return toDtoWithPhotos(playground);
    }

    @Override
    public List<PlaygroundDto> listPlayground() {
        List<Playground> playgrounds = repository.findAll();
        return playgrounds.stream().map(this::toDtoWithPhotos).toList();
    }

    @Override
    public void deletePlayground(Long id) {
        repository.deleteById(id);
    }

    private PlaygroundDto toDtoWithPhotos(Playground playground) {
        PlaygroundDto dto = mapper.toDto(playground);
        dto.setPhotos(
                playground.getPhotos().stream()
                        .map(PlaygroundPhoto::getUrl)
                        .toList());
        return dto;
    }

    @Override
    public PlaygroundDto addPhoto(Long playgroundId, String photoUrl) {
        Playground playground = repository.findById(playgroundId)
                .orElseThrow(() -> new RuntimeException("Playground not found"));

        PlaygroundPhoto photo = new PlaygroundPhoto();
        photo.setUrl(photoUrl);
        photo.setPlayground(playground);

        playground.getPhotos().add(photo);

        Playground saved = repository.save(playground);

        return toDtoWithPhotos(saved);
    }

    @Override
    public PlaygroundDto removePhoto(Long playgroundId, String photoUrl) {
        Playground playground = repository.findById(playgroundId)
                .orElseThrow(() -> new RuntimeException("Playground not found"));

        playground.getPhotos().removeIf(photo -> photo.getUrl().equals(photoUrl));

        Playground saved = repository.save(playground);

        return toDtoWithPhotos(saved);
    }
}
