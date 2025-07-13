package org.example.userservice.service.impl;

import org.example.userservice.model.User;
import org.example.userservice.repository.UserRepository;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUser(String userId, String password) {
        return userRepository.findByUseridAndPassword(userId, password);
    }

    @Override
    public void updateInfo(User user) {
        userRepository.save(user); // JPA会自动判断是新增还是更新
    }

    @Override
    public boolean register(User user) {
        // 检查用户是否已存在
        if (userRepository.existsByUserid(user.getUserid())) {
            return false;
        }

        try {
            user.setDeltag(1); // 设置默认状态
            userRepository.save(user);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public User getUserById(String userId) {
        return userRepository.findByUserid(userId);
    }
}