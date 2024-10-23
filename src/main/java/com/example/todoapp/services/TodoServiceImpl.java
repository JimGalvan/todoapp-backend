package com.example.todoapp.services;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.repositories.TodoRepository;
import com.example.todoapp.services.interfaces.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Override
    public Todo createTodo(Todo todo) {
        return todoRepository.save(todo);
    }

    @Override
    public Todo updateTodo(UUID id, boolean isCompleted) {
        Todo todo = todoRepository.findById(id).orElseThrow();
        todo.setCompleted(isCompleted);
        return todoRepository.save(todo);
    }

    @Override
    public void deleteTodoById(UUID id) {
        todoRepository.deleteById(id);
    }

    @Override
    public Todo getTodoById(UUID id) {
        return todoRepository.findById(id).orElse(null);
    }

    @Override
    public List<Todo> getAllTodos() {
        return todoRepository.findAll();
    }
}
