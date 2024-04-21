package com.john.springdatacache.service;

import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserTypeService {

    private final UserTypeRepository userTypeRepository;

    // OPTION #2 - Creating a new cache record, at the same time the data it's been created in the database.
    // Adds a new value to the Cache.
    public UserTypeEntity createUserType(String type, String description) {
        UserTypeEntity userTypeEntity = userTypeRepository.findByType(type);
        if (userTypeEntity != null) {
            userTypeEntity.setDescription(description);
            userTypeEntity.setLastUpdatedBy("berto");
            userTypeEntity.setLastUpdatedDatetime(LocalDateTime.now());
        } else {
            userTypeEntity = new UserTypeEntity();
            userTypeEntity.setDescription(description);
            userTypeEntity.setType(type);
            userTypeEntity.setCreatedBy("john");
            userTypeEntity.setCreatedDatetime(LocalDateTime.now());
        }
        return userTypeRepository.save(userTypeEntity);
    }

    public List<UserTypeEntity> getAllUserType() {
        return userTypeRepository.findAll();
    }

    @Transactional
    public void deleteUserType(String type) {
        userTypeRepository.deleteByType(type);
    }

}
