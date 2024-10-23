package com.example.todoapp.controllers;

import com.example.todoapp.entities.TodoList;
import com.example.todoapp.entities.User;
import com.example.todoapp.services.interfaces.TodoListService;
import com.example.todoapp.services.interfaces.UserService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final TodoListService todoListService;

    public UserController(UserService userService, TodoListService todoListService) {
        this.userService = userService;
        this.todoListService = todoListService;
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable UUID id, @RequestBody User user) {
        return userService.updateUser(id, user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable UUID id) {
        userService.deleteUser(id);
    }

    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable UUID id) {
        return userService.getUserById(id);
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}/todolists")
    public List<TodoList> getAllTodoListsByUserId(@AuthenticationPrincipal Jwt jwt, @PathVariable String id) {
        return todoListService.getAllTodoListsByUserId(UUID.fromString(id));
    }

    @GetMapping("/{id}/profile")
    public User getUserProfile(@PathVariable String id) {
        return userService.getUserById(UUID.fromString(id)).orElseThrow();
    }
}
