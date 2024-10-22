package com.example.todoapp.repositories;


import com.example.todoapp.entities.TodoList;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface TodoListRepository extends JpaRepository<TodoList, UUID> {
    Optional<TodoList> findByNameAndUserId(String name, UUID userId);
}
