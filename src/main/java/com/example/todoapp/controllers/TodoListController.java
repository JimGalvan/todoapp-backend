package com.example.todoapp.controllers;

import com.example.todoapp.dtos.todolist.CreateTodoListResponseDto;
import com.example.todoapp.entities.Todo;
import com.example.todoapp.entities.TodoList;
import com.example.todoapp.services.interfaces.TodoListService;
import com.example.todoapp.utils.JwtUtils;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

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
    public CreateTodoListResponseDto createTodoList(@AuthenticationPrincipal Jwt jwt, @RequestBody TodoList todoList) {
        TodoList createdTodoList = todoListService.createTodoList(JwtUtils.getUserIdFromJwt(jwt), todoList);
        return new CreateTodoListResponseDto(createdTodoList);
    }

    @GetMapping("/{listId}/todos")
    public List<Todo> getAllTodosFromTodoList(@PathVariable UUID listId) {
        return todoListService.getAllTodosFromTodoList(listId);
    }

    @PutMapping("/{listId}")
    public TodoList updateTodoList(@PathVariable UUID listId, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(listId, todoList);
    }

    @DeleteMapping("/{listId}")
    public void deleteTodoListById(@PathVariable UUID listId) {
        todoListService.deleteTodoListById(listId);
    }

    @GetMapping("/{listId}")
    public TodoList getTodoListById(@PathVariable UUID listId) {
        return todoListService.getTodoListById(listId);
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }
}
