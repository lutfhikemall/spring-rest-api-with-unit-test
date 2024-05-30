package com.test.indivara.test_indivara.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.indivara.test_indivara.dto.request.CreateUserRequest;
import com.test.indivara.test_indivara.dto.request.LoginUserRequest;
import com.test.indivara.test_indivara.dto.response.CreateUserResponse;
import com.test.indivara.test_indivara.dto.response.LoginUserResponse;
import com.test.indivara.test_indivara.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public CreateUserResponse createUser(@Valid @RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @PostMapping("/login")
    public LoginUserResponse loginUser(@Valid @RequestBody LoginUserRequest request) {
        return userService.loginUser(request);
    }

}
