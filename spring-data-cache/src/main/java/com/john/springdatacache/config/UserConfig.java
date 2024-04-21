package com.john.springdatacache.config;

import com.john.springdatacache.entity.UserTypeEntity;
import com.john.springdatacache.repository.UserTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.Cache;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.caffeine.CaffeineCacheManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.List;

@Configuration
@EnableCaching
@EnableTransactionManagement
@Log4j2
@RequiredArgsConstructor
public class UserConfig {

    private final UserTypeRepository userTypeRepository;

    private final CaffeineCacheManager cacheManager;

    // OPTION #2 -  Loads at boot up all the values for User Type from the DB, that way they are ready to use.
    @PostConstruct
    public void loadCache() {
        log.info("Loading user types");
        List<UserTypeEntity> userTypes = userTypeRepository.findAll();
        Cache cache = cacheManager.getCache("initUserTypeCache");
        if (cache != null) {
            userTypes.forEach(userType -> cache.put(userType.getType(), userType));
            log.info("Loaded {} user types to cache.", userTypes.size());
        } else {
            log.error("Cache didn't load properly.");
        }
    }
}
