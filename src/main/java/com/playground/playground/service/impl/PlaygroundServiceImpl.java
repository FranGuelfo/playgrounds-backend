package com.playground.playground.service.impl;

import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.mapper.PlaygroundMapper;
import com.playground.playground.model.entity.Playground;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PlaygroundServiceImpl implements PlaygroundService {

    private final PlaygroundRepository repository;

    private final PlaygroundMapper mapper;

    @Override
    public List<PlaygroundDto> listPlayground() {
        List<Playground> playgrounds = repository.findAll();
        return mapper.toDtos(playgrounds);
    }

    @Override
    public PlaygroundDto getPlaygrounds(Long id) {
        Playground playground = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playground not found"));
        return mapper.toDto(playground);
    }

    @Override
    public PlaygroundDto createPlayground(PlaygroundDto playgroundDto) {
        Playground playground = mapper.toEntity(playgroundDto);
        playground.setValorationMedia((0.0));

        Playground savedPlayground = repository.save(playground);

        return mapper.toDto(savedPlayground);
    }

    @Override
    public PlaygroundDto updatePlayground(Long id, PlaygroundDto playground) {

        Playground existPlayground = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playground not found"));

        existPlayground.setName(playground.getName());
        existPlayground.setAddress(playground.getAddress());
        existPlayground.setLatitude(playground.getLatitude());
        existPlayground.setLongitude(playground.getLongitude());
        existPlayground.setDescription(playground.getDescription());
        existPlayground.setPhotos(playground.getPhotos());

        Playground savedPlayground = repository.save(existPlayground);

        return mapper.toDto(savedPlayground);
    }

    @Override
    public void deletePlayground(Long id) {
        repository.deleteById(id);
    }
}
