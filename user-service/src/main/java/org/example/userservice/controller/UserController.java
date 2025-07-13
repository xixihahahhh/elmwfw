package org.example.userservice.controller;

import org.example.userservice.dto.LoginRequest;
import org.example.userservice.model.User;
import org.example.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest request) {
        User user = userService.findUser(request.getUserid(), request.getPassword());
        return ResponseEntity.ok(user);
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        boolean success = userService.register(user);
        return success ? ResponseEntity.ok().build() : ResponseEntity.badRequest().body("用户已存在");
    }

    @PutMapping("/update")
    public ResponseEntity<Void> update(@RequestBody User user) {
        userService.updateInfo(user);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getById(@PathVariable String userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}