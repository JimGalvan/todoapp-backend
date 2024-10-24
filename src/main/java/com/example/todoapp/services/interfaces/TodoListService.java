package com.example.todoapp.services.interfaces;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.entities.TodoList;

import java.util.List;
import java.util.UUID;

public interface TodoListService {
    TodoList createTodoList(UUID userId, TodoList todoList);

    TodoList updateTodoList(UUID id, TodoList todoList);

    void deleteTodoListById(UUID id);

    TodoList getTodoListById(UUID id);

    List<TodoList> getAllTodoLists(UUID userId);

    List<TodoList> getAllTodoListsByUserId(UUID userId);

    Todo addTodoToTodoList(UUID id, Todo todo);

    List<Todo> getAllTodosFromTodoList(UUID listId);
}