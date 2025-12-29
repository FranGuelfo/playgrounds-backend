package com.playground.playground.service.impl;

import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.dto.UpdatePlaygroundDto;
import com.playground.playground.exception.PlaygroundNotFoundException;
import com.playground.playground.mapper.PlaygroundMapper;
import com.playground.playground.domain.entity.Playground;
import com.playground.playground.domain.entity.PlaygroundPhoto;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PlaygroundServiceImpl implements PlaygroundService {

    private final PlaygroundRepository repository;
    private final PlaygroundMapper mapper;

    @Override
    @Transactional
    public PlaygroundDto createPlayground(CreatePlaygroundDto  dto) {
        Playground playground = mapper.toEntity(dto);
        playground.setValorationMedia(0.0);
        playground.setCreatedAt(LocalDateTime.now());

        return mapper.toDto(repository.save(playground));
    }

    @Override
    @Transactional
    public PlaygroundDto updatePlayground(String id, UpdatePlaygroundDto dto) {
        Playground playground = repository.findById(id)
                .orElseThrow(PlaygroundNotFoundException::new);

        playground.setName(dto.getName());
        playground.setAddress(dto.getAddress());
        playground.setLatitude(dto.getLatitude());
        playground.setLongitude(dto.getLongitude());
        playground.setDescription(dto.getDescription());

        playground.setPhotos(mapper.mapPhotos(dto.getPhotos()));

        return mapper.toDto(playground);
    }

    @Override
    public PlaygroundDto getPlaygrounds(String id) {
        return repository.findById(id)
                .map(mapper::toDto)
                .orElseThrow(PlaygroundNotFoundException::new);
    }

    @Override
    public List<PlaygroundDto> listPlayground() {
        return repository.findAll()
                .stream()
                .map(mapper::toDto)
                .toList();
    }

    @Override
    public void deletePlayground(String id) {
        if (!repository.existsById(id)) {
            throw new PlaygroundNotFoundException();
        }
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public PlaygroundDto addPhoto(String playgroundId, String photoUrl) {
        Playground playground = repository.findById(playgroundId)
                .orElseThrow(PlaygroundNotFoundException::new);

        playground.getPhotos().add(new PlaygroundPhoto(photoUrl));

        return mapper.toDto(playground);
    }

    @Override
    @Transactional
    public PlaygroundDto removePhoto(String playgroundId, String photoUrl) {
        Playground playground = repository.findById(playgroundId)
                .orElseThrow(PlaygroundNotFoundException::new);

        playground.getPhotos()
                .removeIf(photo -> photo.getUrl().equals(photoUrl));

        return mapper.toDto(playground);
    }
}
