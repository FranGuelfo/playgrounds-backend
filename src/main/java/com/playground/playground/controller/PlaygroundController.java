package com.playground.playground.controller;

import com.playground.playground.model.entity.Playground;
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
    public List<Playground> listPlayground(){
        return service.listPlayground();
    }

    @GetMapping("/{id}")
    public Playground getPlaygrounds(@PathVariable Long id) {
        return service.getPlaygrounds(id);
    }

    @PostMapping
    public Playground createPlayground(@RequestBody Playground playground){
        return service.createPlayground(playground);
    }

    @PutMapping
    public Playground updatePlayground(@PathVariable Long id, @RequestBody Playground playground){
        return service.updatePlayground(id, playground);
    }

    @DeleteMapping("/{id}")
    public void deletePlayground(@PathVariable Long id){
        service.deletePlayground(id);
    }
}
