package com.example.todoapp.services.interfaces;

import com.example.todoapp.entities.User;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface UserService {
    List<User> getAllUsers();

    Optional<User> getUserById(UUID id);

    User createUser(User user);

    User updateUser(UUID id, User user);

    void deleteUser(UUID id);

    User registerUser(String username, String rawPassword, List<String> authorities);
}
