package com.example.todoapp.dtos.todolist;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CreateTodoListDto {

    @NotBlank(message = "Username is mandatory")
    @Size(min = 1, max = 50, message = "Username must be between 1 and 50 characters")
    private String name;
}
