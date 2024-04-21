package com.john.springdatacache.controller;

import com.john.springdatacache.entity.UserEntity;
import com.john.springdatacache.service.User;
import com.john.springdatacache.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public List<UserEntity> getAllUsers(){
        log.info("Getting all the users from the database.");
        return userService.executeGetUsersResource();
    }

    @PostMapping
    public UserEntity createUser(@RequestBody User user){
        return userService.executeCreateUser(user.getFirstName(), user.getLastName(), user.getType());
    }
}
