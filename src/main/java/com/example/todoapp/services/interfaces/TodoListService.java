package com.example.todoapp.services.interfaces;

import com.example.todoapp.entities.TodoList;

import java.util.List;
import java.util.UUID;

public interface TodoListService {
    TodoList createTodoList(TodoList todoList, UUID userId);

    TodoList updateTodoList(UUID id, TodoList todoList);

    void deleteTodoListById(UUID id);

    TodoList getTodoListById(UUID id);

    List<TodoList> getAllTodoLists();
}