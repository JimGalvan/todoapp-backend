package com.example.todoapp.dtos;

import java.time.Instant;
import java.util.UUID;

public class BaseDto {
    private final UUID id;
    private final Instant createdAt;
    private final Instant updatedAt;

    public BaseDto(UUID id, Instant createdAt, Instant updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public UUID getId() {
        return id;
    }

    public Instant getCreatedAt() {
        return createdAt;
    }

    public Instant getUpdatedAt() {
        return updatedAt;
    }

}
