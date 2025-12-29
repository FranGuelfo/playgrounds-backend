package com.playground.playground.repository;

import com.playground.playground.security.user.UserSecurity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<UserSecurity, String> {

    Optional<UserSecurity> findByUsername(String username);
}