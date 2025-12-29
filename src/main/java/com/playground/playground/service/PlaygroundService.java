package com.playground.playground.service;


import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.dto.UpdatePlaygroundDto;

import java.util.List;

public interface PlaygroundService {

    List<PlaygroundDto> listPlayground();

    PlaygroundDto getPlaygrounds(String id);

    PlaygroundDto createPlayground(CreatePlaygroundDto  playground);

    PlaygroundDto updatePlayground(String id, UpdatePlaygroundDto playground);

    void deletePlayground(String id);

    PlaygroundDto addPhoto(String playgroundId, String photoUrl);

    PlaygroundDto removePhoto(String playgroundId, String photoUrl);
}
