package com.john.springdatacache.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

/**
 * OPTION #1 - loading the cache as it comes from the database, request by the service. New request, will be fetched
 * from the database and then cache.
 */
@Component
@RequiredArgsConstructor
@Log4j2
public class UserTypeCacheProxyHandler {

/*    private final UserTypeRepository userTypeRepository;

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
        return userTypeRepository.findByType(type);
    }

    @CachePut(value = "initUserTypeCache", key = "#userTypeEntity.type")
    public UserTypeEntity updateUserTypeCache(UserTypeEntity userTypeEntity) {
        log.info("Cache for User Type was updated: {}", userTypeEntity.getType());
        return userTypeRepository.save(userTypeEntity);
    }*/
}
