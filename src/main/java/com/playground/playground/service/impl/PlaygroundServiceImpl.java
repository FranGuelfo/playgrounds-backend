package com.playground.playground.service.impl;

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

    @Override
    public List<Playground> listPlayground() {
        return repository.findAll();
    }

    @Override
    public Playground getPlaygrounds(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Playground not found"));
    }

    @Override
    public Playground createPlayground(Playground playground) {
        return repository.save(playground);
    }

    @Override
    public Playground updatePlayground(Long id, Playground playground) {

        Playground existPlayground = getPlaygrounds(id);

        existPlayground.setName(playground.getName());
        existPlayground.setAddress(playground.getAddress());
        existPlayground.setLatitude(playground.getLatitude());
        existPlayground.setLongitude(playground.getLongitude());
        existPlayground.setDescription(playground.getDescription());
        existPlayground.setPhotos(playground.getPhotos());

        return repository.save(existPlayground);
    }

    @Override
    public void deletePlayground(Long id) {
        repository.deleteById(id);
    }
}
