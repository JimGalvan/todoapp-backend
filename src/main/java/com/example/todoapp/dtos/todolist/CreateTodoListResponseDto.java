package com.example.todoapp.dtos.todolist;

import com.example.todoapp.dtos.BaseDto;
import com.example.todoapp.entities.TodoList;

public class CreateTodoListResponseDto extends BaseDto {

    private final String name;

    public CreateTodoListResponseDto(TodoList todoList) {
        super(todoList.getId(), todoList.getCreatedAt(), todoList.getUpdatedAt());
        this.name = todoList.getName();
    }

    public String getName() {
        return name;
    }
}