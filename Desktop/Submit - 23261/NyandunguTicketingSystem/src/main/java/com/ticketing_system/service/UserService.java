package com.ticketing_system.service;

import com.ticketing_system.model.User;

import java.util.List;

public interface UserService {
    User createUser(User user);

    User updateUser(User user);
    User authenticateUser(User user);
    List<User> getAllUsers();
    User getUserById(Long userId);
    User getCurrentUser();
}
