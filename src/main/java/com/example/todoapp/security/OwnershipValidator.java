package com.example.todoapp.security;

import com.example.todoapp.services.interfaces.TodoListService;
import com.example.todoapp.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component("ownershipValidator")
public class OwnershipValidator {

    private final TodoListService todoListService;

    @Autowired
    public OwnershipValidator(TodoListService todoListService) {
        this.todoListService = todoListService;
    }

    public boolean validateTodoListOwner(UUID listId, Jwt jwt) {
        UUID userId = JwtUtils.getUserIdFromJwt(jwt);
        return todoListService.getTodoListById(listId).getUser().getId().equals(userId);
    }
}