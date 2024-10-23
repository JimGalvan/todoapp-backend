package com.example.todoapp.dtos.auth;

import java.util.UUID;

public class TokenResponseDto {
    private String token;
    private UUID userId;

    public TokenResponseDto(String token) {
        this.token = token;
    }

    public TokenResponseDto(String token, UUID userId) {
        this.token = token;
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }
}
