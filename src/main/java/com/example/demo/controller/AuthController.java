package com.example.demo.controller;

import com.example.demo.dto.AuthRequest;
import com.example.demo.dto.RegisterUserRequest;
import com.example.demo.model.User;
import com.example.demo.security.JwtService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @GetMapping("/openAccess")
    public String openAccess(){
        return "open access";
    }

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterUserRequest user) {
        return userService.createUser(user);
    }

    @GetMapping("/user")
//    @PreAuthorize("hasAuthority('USER')")
    public String homePage() {
        return "homePage";
    }

    @GetMapping("/admin")
//    @PreAuthorize("hasAuthority('ADMIN')")
    public String adminPage() {
        return "adminPage";
    }

    @GetMapping("/hello/abc")
    public String hello(){
        return "hello Hari";
    }

    @PostMapping("/authenticate")
    public String authenticateAndGetToken(@RequestBody AuthRequest authRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPass()));
        if (authentication.isAuthenticated()) {
            return jwtService.generateToken(authRequest.getEmail());
        } else {
            throw new UsernameNotFoundException("invalid user name or password");
        }
    }
}
