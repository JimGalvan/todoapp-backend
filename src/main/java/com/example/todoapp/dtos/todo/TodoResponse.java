package com.example.todoapp.dtos.todo;

import com.example.todoapp.entities.Todo;

public class TodoResponse {
    private String title;
    private String description;
    private boolean isCompleted;

    public TodoResponse() {
    }

    public static TodoResponse fromEntity(Todo todo) {
        TodoResponse response = new TodoResponse();
        response.setTitle(todo.getTitle());
        response.setDescription(todo.getDescription());
        response.setCompleted(todo.isCompleted());
        return response;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }

    @Override
    public String toString() {
        return "TodoResponse{" +
                "title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", isCompleted=" + isCompleted +
                '}';
    }
}
