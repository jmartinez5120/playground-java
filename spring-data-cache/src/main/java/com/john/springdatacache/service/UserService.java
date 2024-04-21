package com.john.springdatacache.service;

import com.john.springdatacache.entity.UserEntity;
import com.john.springdatacache.entity.UserType;
import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.repository.UserRepository;
import com.john.springdatacache.repository.UserTypeRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UserTypeCacheProxyHandler userTypeCacheProxyHandler;

    private final UserRepository userRepository;

    public List<UserEntity> executeGetUsersResource(){
        return userRepository.findAll();
    }

    public UserEntity executeCreateUser(String firstName, String lastName, String userType){
        UserTypeEntity userTypeEntity = userTypeCacheProxyHandler.getUserTypeByType(userType);
        UserEntity userEntity = new UserEntity();
        userEntity.setFirstName(firstName);
        userEntity.setLastName(lastName);
        userEntity.setUserType(userTypeEntity);
        userEntity.setCreatedBy("john r");
        userEntity.setCreatedDatetime(LocalDateTime.now());
        userRepository.save(userEntity);
        return userEntity;
    }


}
