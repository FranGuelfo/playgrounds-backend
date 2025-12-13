package com.playground.playground.service.security;

import com.playground.playground.model.security.UserSecurity;
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
