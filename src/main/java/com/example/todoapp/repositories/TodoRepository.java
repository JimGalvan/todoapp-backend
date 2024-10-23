package com.example.todoapp.repositories;

import com.example.todoapp.entities.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface TodoRepository extends JpaRepository<Todo, UUID> {
    List<Todo> findByListId(UUID listId);

    void deleteByListId(UUID id);
}
