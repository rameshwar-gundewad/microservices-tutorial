package com.microservices.user.services.service;

import com.microservices.user.services.entity.User;

import java.util.List;

public interface UserService {

    public User saveUser(User user);

    public User findUserById(long userId);

    public List<User> findAllUser();
}
