package com.playground.playground.repository;

import com.playground.playground.domain.entity.Playground;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PlaygroundRepository extends MongoRepository<Playground, String> {

}
