package com.example.todoapp.dtos.todo;

public class UpdateTodoDto {
    private boolean isCompleted;

    // No-argument constructor
    public UpdateTodoDto() {
    }

    // Getter and setter methods
    public boolean getIsCompleted() {
        return isCompleted;
    }

    public void setIsCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }
}