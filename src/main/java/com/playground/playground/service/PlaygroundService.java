package com.playground.playground.service;


import com.playground.playground.model.entity.Playground;

import java.util.List;

public interface PlaygroundService {

    List<Playground> listPlayground();
    Playground getPlaygrounds(Long id);

    Playground createPlayground(Playground playground);

    Playground updatePlayground(Long id, Playground playground);

    void deletePlayground(Long id);
}
