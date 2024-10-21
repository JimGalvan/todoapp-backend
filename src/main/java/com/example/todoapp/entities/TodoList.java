package com.example.todoapp.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Id;

import java.util.UUID;

@Entity
public class TodoList {
    @Id
    @GeneratedValue
    private UUID Id;

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public void setId(UUID id) {
        this.Id = id;
    }

    public UUID getId() {
        return Id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    @Override
    public String toString() {
        return String.format(
                "TodoList[id=%s, name='%s']",
                Id, name);
    }
}
