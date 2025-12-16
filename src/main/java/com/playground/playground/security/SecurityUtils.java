package com.playground.playground.security;

import com.playground.playground.exception.ForbiddenException;
import com.playground.playground.security.user.UserSecurity;
import org.springframework.security.core.context.SecurityContextHolder;

public class SecurityUtils {

    private SecurityUtils() {}

    public static UserSecurity getCurrentUser() {
        var auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth == null || !auth.isAuthenticated()) {
            throw new ForbiddenException("No authenticated user found");
        }

        Object principal = auth.getPrincipal();
        if (principal instanceof UserSecurity user) {
            return user;
        }

        throw new ForbiddenException("User not found in context");
    }
}
