package com.example.todoapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class TodoList extends BaseEntity {

    private String name;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @JsonIgnore
    @OneToMany(mappedBy = "list", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Todo> todos;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Todo> getTodos() {
        return todos;
    }

    public void setTodos(List<Todo> todos) {
        this.todos = todos;
    }

    // Check if a user is the owner
    public boolean isOwner(User user) {
        return this.user.equals(user);
    }

    public void addTodo(Todo todo) {
        todos.add(todo);
        todo.setList(this);
    }

    public void removeTodo(Todo todo) {
        todos.remove(todo);
        todo.setList(null);
    }


    @Override
    public String toString() {
        return String.format(
                "TodoList[id=%s, name='%s']", id, name);
    }
}
