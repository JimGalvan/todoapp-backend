package com.example.todoapp.services;

import com.example.todoapp.entities.TodoList;
import com.example.todoapp.repositories.TodoListRepository;
import com.example.todoapp.services.interfaces.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    @Autowired
    private TodoListRepository todoListRepository;

    @Override
    public TodoList createTodoList(TodoList todoList) {
        return todoListRepository.save(todoList);
    }

    @Override
    public TodoList updateTodoList(UUID id, TodoList todoList) {
        todoList.setId(id);
        return todoListRepository.save(todoList);
    }

    @Override
    public void deleteTodoListById(UUID id) {
        todoListRepository.deleteById(id);
    }

    @Override
    public TodoList getTodoListById(UUID id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Override
    public List<TodoList> getAllTodoLists() {
        return todoListRepository.findAll();
    }
}
