package com.example.todoapp.services.interfaces;

import com.example.todoapp.entities.Todo;

import java.util.List;
import java.util.UUID;

public interface TodoService {
    Todo createTodo(Todo todo);

    Todo updateTodo(UUID id, Todo todo);

    void deleteTodoById(UUID id);

    Todo getTodoById(UUID id);

    List<Todo> getAllTodos();
}