package com.john.springdatacache.controller;

import com.john.springdatacache.entity.UserEntity;
import com.john.springdatacache.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        return userService.executeGetUsersResource();
    }
}
