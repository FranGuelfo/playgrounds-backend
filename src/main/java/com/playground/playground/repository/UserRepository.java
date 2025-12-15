package com.playground.playground.repository;

import com.playground.playground.security.user.UserSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserSecurity, Long> {
    Optional<UserSecurity> findByUsername(String username);
}