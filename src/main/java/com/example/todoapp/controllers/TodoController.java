package com.example.todoapp.controllers;

import com.example.todoapp.dtos.todo.TodoResponse;
import com.example.todoapp.dtos.todo.UpdateTodoDto;
import com.example.todoapp.entities.Todo;
import com.example.todoapp.services.interfaces.TodoListService;
import com.example.todoapp.services.interfaces.TodoService;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/todo-lists/{listId}/todo")
public class TodoController {

    private final TodoService todoService;
    private final TodoListService todoListService;

    public TodoController(TodoService todoService, TodoListService todoListService) {
        this.todoService = todoService;
        this.todoListService = todoListService;
    }

    @PostMapping
    public Todo addTodoToTodoList(@PathVariable UUID listId, @RequestBody Todo todo) {
        return todoListService.addTodoToTodoList(listId, todo);
    }

    @PutMapping("/{todoId}")
    public TodoResponse updateTodo(@PathVariable String listId, @PathVariable UUID todoId, @RequestBody UpdateTodoDto request) {
        boolean isCompleted = request.getIsCompleted();
        Todo todo = todoService.updateTodo(todoId, isCompleted);
        return TodoResponse.fromEntity(todo);
    }

    @DeleteMapping("/{todoId}")
    public void deleteTodoById(@PathVariable String listId, @PathVariable UUID todoId) {
        todoService.deleteTodoById(todoId);
    }

    @GetMapping("/{todoId}")
    public Todo getTodoById(@PathVariable String listId, @PathVariable UUID todoId) {
        return todoService.getTodoById(todoId);
    }
}
