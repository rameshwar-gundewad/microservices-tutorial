package com.microservices.user.services.controller;

import com.microservices.user.services.entity.User;
import com.microservices.user.services.service.UserService;
import com.microservices.user.services.service.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserService userService;


    @PostMapping
    public ResponseEntity<User> saveUser(@RequestBody User user) {
        logger.info("UserController :: got the Request Body :: {}", user);
        user = userService.saveUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserDetail(@PathVariable long userId) {
        User user = userService.findUserById(userId);
        return ResponseEntity.ok(user);
    }

    @GetMapping
    public ResponseEntity<List<User>> getAllUser() {
        List<User> user = userService.findAllUser();
        return ResponseEntity.ok(user);
    }



}
