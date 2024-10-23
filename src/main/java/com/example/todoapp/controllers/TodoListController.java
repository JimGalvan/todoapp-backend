package com.example.todoapp.controllers;

import com.example.todoapp.dtos.todolist.CreateTodoListResponseDto;
import com.example.todoapp.entities.Todo;
import com.example.todoapp.entities.TodoList;
import com.example.todoapp.services.interfaces.TodoListService;
import com.example.todoapp.utils.JwtUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todo-lists")
public class TodoListController {

    private final TodoListService todoListService;

    public TodoListController(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    @PostMapping
    public CreateTodoListResponseDto createTodoList(@RequestBody TodoList todoList, @AuthenticationPrincipal Jwt jwt) {
        TodoList createdTodoList = todoListService.createTodoList(JwtUtils.getUserIdFromJwt(jwt), todoList);
        return new CreateTodoListResponseDto(createdTodoList);
    }

    @GetMapping("/{listId}/todos")
    @PreAuthorize("@ownershipValidator.validateTodoListOwner(#listId, #jwt)")
    public List<Todo> getAllTodosFromTodoList(@PathVariable UUID listId, @AuthenticationPrincipal Jwt jwt) {
        return todoListService.getAllTodosFromTodoList(listId);
    }

    @PutMapping("/{listId}")
    @PreAuthorize("@ownershipValidator.validateTodoListOwner(#listId, #jwt)")
    public TodoList updateTodoList(@PathVariable UUID listId, @RequestBody TodoList todoList, @AuthenticationPrincipal Jwt jwt) {
        return todoListService.updateTodoList(listId, todoList);
    }

    @DeleteMapping("/{listId}")
    @PreAuthorize("@ownershipValidator.validateTodoListOwner(#listId, #jwt)")
    public void deleteTodoListById(@PathVariable UUID listId, @AuthenticationPrincipal Jwt jwt) {
        todoListService.deleteTodoListById(listId);
    }

    @GetMapping("/{listId}")
    @PreAuthorize("@ownershipValidator.validateTodoListOwner(#listId, #jwt)")
    public TodoList getTodoListById(@PathVariable UUID listId, @AuthenticationPrincipal Jwt jwt) {
        return todoListService.getTodoListById(listId);
    }

    @GetMapping
    public List<TodoList> getAllTodoLists(@AuthenticationPrincipal Jwt jwt) {
        return todoListService.getAllTodoLists(JwtUtils.getUserIdFromJwt(jwt));
    }
}
