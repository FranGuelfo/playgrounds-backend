package com.playground.playground.controller;

import com.playground.playground.dto.PlaygroundDto;
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
        when(service.listPlayground()).thenReturn(List.of(new PlaygroundDto("id", "name", "address",
                (double) 0, (double) 0, "description", (double) 0, List.of("photos"))));

        List<PlaygroundDto> result = playgroundController.listPlaygrounds();
        Assertions.assertEquals(List.of(new PlaygroundDto("id", "name", "address", Double.valueOf(0),
                (double) 0, "description", (double) 0, List.of("photos"))), result);
    }

    @Test
    void testGetPlayground() {
        when(service.getPlaygrounds(anyString())).thenReturn(new PlaygroundDto("id", "name", "address",
                Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));

        PlaygroundDto result = playgroundController.getPlayground("id");
        Assertions.assertEquals(new PlaygroundDto("id", "name", "address", Double.valueOf(0), Double.valueOf(0),
                "description", Double.valueOf(0), List.of("photos")), result);
    }

    @Test
    void testCreatePlayground() {
        when(service.createPlayground(any(PlaygroundDto.class))).thenReturn(new PlaygroundDto("id", "name", "address"
                , Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));

        PlaygroundDto result = playgroundController.createPlayground(new PlaygroundDto("id", "name", "address",
                Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));
        Assertions.assertEquals(new PlaygroundDto("id", "name", "address", Double.valueOf(0), Double.valueOf(0),
                "description", Double.valueOf(0), List.of("photos")), result);
    }

    @Test
    void testUpdatePlayground() {
        when(service.updatePlayground(anyString(), any(PlaygroundDto.class))).thenReturn(new PlaygroundDto("id",
                "name", "address", Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of(
                        "photos")));

        PlaygroundDto result = playgroundController.updatePlayground("id", new PlaygroundDto("id", "name", "address",
                Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));
        Assertions.assertEquals(new PlaygroundDto("id", "name", "address", Double.valueOf(0), Double.valueOf(0),
                "description", Double.valueOf(0), List.of("photos")), result);
    }

    @Test
    void testDeletePlayground() {
        playgroundController.deletePlayground("id");
        verify(service).deletePlayground(anyString());
    }

    @Test
    void testAddPhoto() {
        when(service.addPhoto(anyString(), anyString())).thenReturn(new PlaygroundDto("id", "name", "address",
                Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));

        PlaygroundDto result = playgroundController.addPhoto("id", "photoUrl");
        Assertions.assertEquals(new PlaygroundDto("id", "name", "address", Double.valueOf(0), Double.valueOf(0),
                "description", Double.valueOf(0), List.of("photos")), result);
    }

    @Test
    void testDeletePhoto() {
        when(service.removePhoto(anyString(), anyString())).thenReturn(new PlaygroundDto("id", "name", "address",
                Double.valueOf(0), Double.valueOf(0), "description", Double.valueOf(0), List.of("photos")));

        PlaygroundDto result = playgroundController.deletePhoto("id", "photoUrl");
        Assertions.assertEquals(new PlaygroundDto("id", "name", "address", Double.valueOf(0), Double.valueOf(0),
                "description", Double.valueOf(0), List.of("photos")), result);
    }
}

//Generated with love by TestMe :) Please raise issues & feature requests at: https://weirddev.com/forum#!/testme