package com.playground.playground.repository;

import com.playground.playground.domain.entity.Playground;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlaygroundRepository extends JpaRepository<Playground, Long> {

}
