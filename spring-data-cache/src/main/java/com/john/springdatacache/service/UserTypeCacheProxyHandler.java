package com.john.springdatacache.service;

import com.john.springdatacache.entity.UserType;
import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.repository.UserTypeRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Log4j2
public class UserTypeCacheProxyHandler {

    private final UserTypeRepository userTypeRepository;

    @Cacheable("userTypes")
    public UserTypeEntity getUserTypeByType(String type) {
        log.info("Fetching user type by type from the database, instead of cache: {}", type);
        // NOTE: This is to test that the first time it fetches the data from the DB, it takes 5 seconds, but the second
        // time it would be instantly.
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            log.error("Interrupted while waiting for user type to fetch", e);
            Thread.currentThread().interrupt();
        }
        return userTypeRepository.findByType(UserType.valueOf(type));
    }
}
