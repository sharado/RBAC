package com.example.demo.service;

import com.example.demo.dto.RegisterUserRequest;
import com.example.demo.model.User;

public interface UserService {
    User createUser(RegisterUserRequest registerUserRequest);
}