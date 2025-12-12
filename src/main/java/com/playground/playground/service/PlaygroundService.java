package com.playground.playground.service;


import com.playground.playground.dto.PlaygroundDto;

import java.util.List;

public interface PlaygroundService {

    List<PlaygroundDto> listPlayground();

    PlaygroundDto getPlaygrounds(Long id);
    PlaygroundDto createPlayground(PlaygroundDto playground);

    PlaygroundDto updatePlayground(Long id, PlaygroundDto playground);

    void deletePlayground(Long id);
}
