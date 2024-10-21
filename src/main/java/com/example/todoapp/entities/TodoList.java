package com.example.todoapp.entities;

import jakarta.persistence.*;

@Entity
public class TodoList extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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
                id, name);
    }
}
