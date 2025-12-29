package com.playground.playground.service;

import com.playground.playground.domain.entity.Playground;
import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.dto.UpdatePlaygroundDto;
import com.playground.playground.exception.PlaygroundNotFoundException;
import com.playground.playground.mapper.PlaygroundMapper;
import com.playground.playground.repository.PlaygroundRepository;
import com.playground.playground.service.impl.PlaygroundServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PlaygroundServiceImplTest {

    @Mock
    private PlaygroundRepository playgroundRepository;

    @Mock
    private PlaygroundMapper playgroundMapper;

    @InjectMocks
    private PlaygroundServiceImpl playgroundService;

    @Test
    void createPlayground_shouldCreatePlaygroundWithDefaultValues() {
        // given
        CreatePlaygroundDto  dto = new CreatePlaygroundDto ();
        dto.setName("Test Playground");
        dto.setPhotos(List.of("url1", "url2"));

        Playground playground = new Playground();
        playground.setPhotos(new ArrayList<>());

        when(playgroundMapper.toEntity(dto)).thenReturn(playground);
        when(playgroundMapper.toDto(any(Playground.class)))
                .thenReturn(new PlaygroundDto());

        when(playgroundRepository.save(any()))
                .thenAnswer(invocation -> invocation.getArgument(0));

        // when
        PlaygroundDto result = playgroundService.createPlayground(dto);

        // then
        assertNotNull(result);
        assertEquals(List.of("url1", "url2"), result.getPhotos());

        verify(playgroundRepository).save(any(Playground.class));
    }


    @Test
    void updatePlayground_shouldThrowException_whenNotFound() {
        // given
        when(playgroundRepository.findById("1")).thenReturn(Optional.empty());

        // then
        assertThrows(
                PlaygroundNotFoundException.class,
                () -> playgroundService.updatePlayground("1", new UpdatePlaygroundDto())
        );
    }
}

