package com.john.springdatacache.service;

import com.john.springdatacache.entity.UserEntity;
import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.repository.UserRepository;
import com.john.springdatacache.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserTypeRepository userTypeRepository;

    private final UserRepository userRepository;

    public List<UserEntity> executeGetUsersResource(){
        return userRepository.findAll();
    }

    @Cacheable("userTypes")
    public UserTypeEntity getUserTypeByType(String type) {
        return userTypeRepository.findByType(type);
    }


}
