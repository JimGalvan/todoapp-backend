package com.example.todoapp.controllers;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.services.interfaces.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PostMapping
    public Todo createTodo(@RequestBody Todo todo) {
        return todoService.createTodo(todo);
    }

    @PutMapping("/{id}")
    public Todo updateTodo(@PathVariable UUID id, @RequestBody Todo todo) {
        return todoService.updateTodo(id, todo);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoById(@PathVariable UUID id) {
        todoService.deleteTodoById(id);
    }

    @GetMapping("/{id}")
    public Todo getTodoById(@PathVariable UUID id) {
        return todoService.getTodoById(id);
    }

    @GetMapping
    public List<Todo> getAllTodos() {
        return todoService.getAllTodos();
    }
}
