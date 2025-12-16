package com.playground.playground.service;


import com.playground.playground.dto.PlaygroundDto;

import java.util.List;

public interface PlaygroundService {

    List<PlaygroundDto> listPlayground();

    PlaygroundDto getPlaygrounds(String id);

    PlaygroundDto createPlayground(PlaygroundDto playground);

    PlaygroundDto updatePlayground(String id, PlaygroundDto playground);

    void deletePlayground(String id);

    PlaygroundDto addPhoto(String playgroundId, String photoUrl);

    PlaygroundDto removePhoto(String playgroundId, String photoUrl);
}
