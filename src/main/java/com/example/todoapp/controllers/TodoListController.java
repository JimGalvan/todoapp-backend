package com.example.todoapp.controllers;

import com.example.todoapp.entities.TodoList;
import com.example.todoapp.services.interfaces.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todolists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public TodoList createTodoList(@AuthenticationPrincipal Jwt jwt, @RequestBody TodoList todoList) {
        String userId = jwt.getClaim("user_id");
        return todoListService.createTodoList(todoList, UUID.fromString(userId));
    }

    @PutMapping("/{id}")
    public TodoList updateTodoList(@PathVariable UUID id, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(id, todoList);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoListById(@PathVariable UUID id) {
        todoListService.deleteTodoListById(id);
    }

    @GetMapping("/{id}")
    public TodoList getTodoListById(@PathVariable UUID id) {
        return todoListService.getTodoListById(id);
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }
}
