package com.example.todoapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class Todo {
    @Id
    @GeneratedValue
    private UUID id;

    private String title;
    private String description;
    private boolean isCompleted;

    @ManyToOne
    @JoinColumn(name = "list_id")
    private TodoList list;

    public void setId(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setCompleted(boolean isCompleted) {
        this.isCompleted = isCompleted;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setList(TodoList list) {
        this.list = list;
    }

    public TodoList getList() {
        return list;
    }

    @Override
    public String toString() {
        return String.format(
                "Todo[id=%s, title='%s', description='%s', isCompleted='%s']",
                id, title, description, isCompleted);
    }
}
