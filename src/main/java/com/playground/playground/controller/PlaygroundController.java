package com.playground.playground.controller;

import com.playground.playground.dto.PlaygroundDto;
import com.playground.playground.service.PlaygroundService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/playground")
@RequiredArgsConstructor
public class PlaygroundController {

    private final PlaygroundService service;

    @GetMapping
    public List<PlaygroundDto> listPlayground(){
        return service.listPlayground();
    }

    @GetMapping("/{id}")
    public PlaygroundDto getPlaygrounds(@PathVariable Long id) {
        return service.getPlaygrounds(id);
    }

    @PostMapping
    public PlaygroundDto createPlayground(@RequestBody PlaygroundDto playground){
        return service.createPlayground(playground);
    }

    @PutMapping
    public PlaygroundDto updatePlayground(@PathVariable Long id, @RequestBody PlaygroundDto playground){
        return service.updatePlayground(id, playground);
    }

    @DeleteMapping("/{id}")
    public void deletePlayground(@PathVariable Long id){
        service.deletePlayground(id);
    }
}
