package com.example.todoapp.services;

import com.example.todoapp.entities.TodoList;
import com.example.todoapp.entities.User;
import com.example.todoapp.repositories.TodoListRepository;
import com.example.todoapp.repositories.UserRepository;
import com.example.todoapp.services.interfaces.TodoListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository, UserRepository userRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TodoList createTodoList(UUID userId, TodoList todoList) {
        User user = userRepository.findById(userId).orElseThrow(() -> new IllegalArgumentException("User not found"));
        Optional<TodoList> existingList = todoListRepository.findByNameAndUserId(todoList.getName(), userId);
        if (existingList.isPresent()) {
            throw new RuntimeException("A list with the same name already exists for this user.");
        }
        todoList.setUser(user);
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
