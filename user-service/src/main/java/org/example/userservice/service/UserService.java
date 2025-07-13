package org.example.userservice.service;


import org.example.userservice.model.User;

public interface UserService {
    User findUser(String userId, String password);

    void updateInfo(User user);

    boolean register(User user);

    User getUserById(String userId);
}
