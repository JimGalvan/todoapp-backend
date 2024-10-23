package com.example.todoapp.services;

import com.example.todoapp.entities.Todo;
import com.example.todoapp.entities.TodoList;
import com.example.todoapp.entities.User;
import com.example.todoapp.repositories.TodoListRepository;
import com.example.todoapp.repositories.UserRepository;
import com.example.todoapp.repositories.TodoRepository;
import com.example.todoapp.services.interfaces.TodoListService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class TodoListServiceImpl implements TodoListService {

    private final TodoListRepository todoListRepository;
    private final UserRepository userRepository;
    private final TodoRepository todoRepository;

    public TodoListServiceImpl(TodoListRepository todoListRepository, UserRepository userRepository, TodoRepository todoRepository) {
        this.todoListRepository = todoListRepository;
        this.userRepository = userRepository;
        this.todoRepository = todoRepository;
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
//        todoRepository.deleteByListId(id);
        todoListRepository.deleteById(id);
    }

    @Override
    public TodoList getTodoListById(UUID id) {
        return todoListRepository.findById(id).orElse(null);
    }

    @Override
    public List<TodoList> getAllTodoLists(UUID userId) {
        return todoListRepository.findAllByUserId(userId);
    }

    @Override
    public List<TodoList> getAllTodoListsByUserId(UUID userId) {
        return todoListRepository.findAllByUserId(userId);
    }

    @Override
    public Todo addTodoToTodoList(UUID id, Todo todo) {
        TodoList todoList = todoListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("List not found"));
        todo.setList(todoList);
        todoRepository.save(todo);
        return todo;
    }

    @Override
    public List<Todo> getAllTodosFromTodoList(UUID listId) {
        return todoRepository.findByListId(listId);
    }
}
