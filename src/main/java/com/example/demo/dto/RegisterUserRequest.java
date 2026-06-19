package com.example.demo.dto;

import lombok.Data;

@Data
public class RegisterUserRequest {
    private String username;
    private String email;
    private String password;
    private Long roleId;
}
