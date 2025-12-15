package com.playground.playground.service;

import com.playground.playground.security.user.UserSecurity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserService {
    private final Map<String, UserSecurity> users = new HashMap<>();

    public UserService() {
        users.put("fran", new UserSecurity());
    }

    public UserSecurity getUser(String username) {
        return users.get(username);
    }
}
