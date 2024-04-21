package com.john.springdatacache.controller;

import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.service.UserTypeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Log4j2
@RequestMapping("/users/types")
@RequiredArgsConstructor
public class UserTypeController {

    private final UserTypeService userTypeService;

    @PostMapping
    public UserTypeEntity createUserType(@RequestBody UserTypeRequest userTypeRequest) {
        return userTypeService.createUserType(userTypeRequest.getType(), userTypeRequest.getDescription());
    }

    @GetMapping
    public List<UserTypeEntity> getAllUserTypes() {
        return userTypeService.getAllUserType();
    }

    @DeleteMapping
    public void deleteUserType(@RequestBody UserTypeRequest userTypeRequest) {
        log.info("Deleting user type {}", userTypeRequest.getType());
        userTypeService.deleteUserType(userTypeRequest.getType());
    }

}
