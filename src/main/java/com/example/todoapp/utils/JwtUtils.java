package com.example.todoapp.utils;

import org.springframework.security.oauth2.jwt.Jwt;

import java.util.UUID;

public class JwtUtils {
    public static UUID getUserIdFromJwt(Jwt jwt) {
        String userId = jwt.getClaim("user_id");
        return UUID.fromString(userId);
    }
}
