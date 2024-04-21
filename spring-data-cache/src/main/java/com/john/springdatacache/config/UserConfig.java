package com.john.springdatacache.config;

import com.john.springdatacache.repository.UserTypeRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Log4j2
@RequiredArgsConstructor
public class UserConfig {

    private final UserTypeRepository userTypeRepository;


    @Bean
    public CacheManager cacheManager() {
        log.info("Creating the cache manager...");
        // Creates the cache manager that is used to store the data.
        return new ConcurrentMapCacheManager("userTypes", "initUserTypeCache");
    }

    // Loads at boot up all the values for User Type from the DB, that way they are ready to use.
    @PostConstruct
    public void loadCache() {
        log.info("Loading user types");
        userTypeRepository.findAll();
    }
}
