package com.example.todoapp.controllers;

import com.example.todoapp.entities.TodoList;
import com.example.todoapp.services.interfaces.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/todolists")
public class TodoListController {

    @Autowired
    private TodoListService todoListService;

    @PostMapping
    public TodoList createTodoList(@RequestBody TodoList todoList) {
        return todoListService.createTodoList(todoList);
    }

    @PutMapping("/{id}")
    public TodoList updateTodoList(@PathVariable UUID id, @RequestBody TodoList todoList) {
        return todoListService.updateTodoList(id, todoList);
    }

    @DeleteMapping("/{id}")
    public void deleteTodoListById(@PathVariable UUID id) {
        todoListService.deleteTodoListById(id);
    }

    @GetMapping("/{id}")
    public TodoList getTodoListById(@PathVariable UUID id) {
        return todoListService.getTodoListById(id);
    }

    @GetMapping
    public List<TodoList> getAllTodoLists() {
        return todoListService.getAllTodoLists();
    }
}
