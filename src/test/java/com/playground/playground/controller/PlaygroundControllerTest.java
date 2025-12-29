package com.playground.playground.controller;

import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.dto.UpdatePlaygroundDto;
import com.playground.playground.service.PlaygroundService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.mockito.Mockito.*;

class PlaygroundControllerTest {
    @Mock
    PlaygroundService service;
    @InjectMocks
    PlaygroundController playgroundController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListPlaygrounds() {
        PlaygroundDto dto = PlaygroundDto.builder()
                .id("id")
                .name("name")
                .address("address")
                .latitude(0.0)
                .longitude(0.0)
                .description("description")
                .valorationMedia(0.0)
                .photos(List.of("photos"))
                .build();

        when(service.listPlayground()).thenReturn(List.of(dto));

        List<PlaygroundDto> result = playgroundController.listPlaygrounds();

        Assertions.assertEquals(List.of(dto), result);
    }

    @Test
    void testGetPlayground() {
        PlaygroundDto dto = PlaygroundDto.builder()
                .id("id")
                .name("name")
                .address("address")
                .latitude(0.0)
                .longitude(0.0)
                .description("description")
                .valorationMedia(0.0)
                .photos(List.of("photos"))
                .build();

        when(service.getPlaygrounds("id")).thenReturn(dto);

        PlaygroundDto result = playgroundController.getPlayground("id");

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testCreatePlayground() {

        PlaygroundDto dto = playgroundDto();
        CreatePlaygroundDto createDto = CreatePlaygroundDto.builder()
                .name("name")
                .address("address")
                .latitude(0.0)
                .longitude(0.0)
                .description("description")
                .photos(List.of("photos"))
                .build();

        when(service.createPlayground(any(CreatePlaygroundDto.class))).thenReturn(dto);

        PlaygroundDto result = playgroundController.createPlayground(createDto);

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testUpdatePlayground() {

        PlaygroundDto dto = playgroundDto();
        UpdatePlaygroundDto updateDto = UpdatePlaygroundDto.builder()
                .name("name")
                .address("address")
                .latitude(0.0)
                .longitude(0.0)
                .description("description")
                .photos(List.of("photos"))
                .build();

        when(service.updatePlayground(eq("id"), any(UpdatePlaygroundDto.class)))
                .thenReturn(dto);

        PlaygroundDto result = playgroundController.updatePlayground("id", updateDto);

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testDeletePlayground() {
        playgroundController.deletePlayground("id");
        verify(service).deletePlayground(anyString());
    }

    @Test
    void testAddPhoto() {
        PlaygroundDto dto = playgroundDto();

        when(service.addPhoto("id", "photoUrl")).thenReturn(dto);

        PlaygroundDto result = playgroundController.addPhoto("id", "photoUrl");

        Assertions.assertEquals(dto, result);
    }

    @Test
    void testDeletePhoto() {
        PlaygroundDto dto = playgroundDto();

        when(service.removePhoto("id", "photoUrl")).thenReturn(dto);

        PlaygroundDto result = playgroundController.deletePhoto("id", "photoUrl");

        Assertions.assertEquals(dto, result);
    }

    private PlaygroundDto playgroundDto() {
        return PlaygroundDto.builder()
                .id("id")
                .name("name")
                .address("address")
                .latitude(0.0)
                .longitude(0.0)
                .description("description")
                .valorationMedia(0.0)
                .photos(List.of("photos"))
                .build();
    }
}