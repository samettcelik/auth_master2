package com.demo.authdemo.controller;

import com.demo.authdemo.entity.User;
import com.demo.authdemo.requests.LoginRequest;
import com.demo.authdemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class LoginController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest user) {
        boolean success = userService.validateUser(user);
        if (success) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.badRequest().body("Login Failed");
        }
    }

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/location/{username}")
    public ResponseEntity<String> getLocationByUsername(@PathVariable String username) {
        Optional<User> user = userService.findByUsername(username);
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get().getLocation());
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
