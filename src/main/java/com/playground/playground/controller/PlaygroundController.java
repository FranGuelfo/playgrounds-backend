package com.playground.playground.controller;

import com.playground.playground.dto.CreatePlaygroundDto;
import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.dto.UpdatePlaygroundDto;
import com.playground.playground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playgrounds")
@RequiredArgsConstructor
public class PlaygroundController {

    private final PlaygroundService service;

    @GetMapping
    public List<PlaygroundDto> listPlaygrounds(){
        return service.listPlayground();
    }

    @GetMapping("/{id}")
    public PlaygroundDto getPlayground(@PathVariable String id) {
        return service.getPlaygrounds(id);
    }

    @PostMapping
    public PlaygroundDto createPlayground(@RequestBody CreatePlaygroundDto  playground){
        return service.createPlayground(playground);
    }

    @PutMapping("/{id}")
    public PlaygroundDto updatePlayground(@PathVariable String id, @RequestBody UpdatePlaygroundDto playground){
        return service.updatePlayground(id, playground);
    }

    @DeleteMapping("/{id}")
    public void deletePlayground(@PathVariable String id){
        service.deletePlayground(id);
    }

    // Opcional: añadir fotos directamente
    @PostMapping("/{id}/photos")
    public PlaygroundDto addPhoto(@PathVariable String id, @RequestBody String photoUrl){
        return service.addPhoto(id, photoUrl);
    }

    @DeleteMapping("/{id}/photos")
    public PlaygroundDto deletePhoto(@PathVariable String id, @RequestBody String photoUrl){
        return service.removePhoto(id, photoUrl);
    }
}

