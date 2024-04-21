package com.john.springdatacache.config;

import lombok.extern.log4j.Log4j2;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
@Log4j2
public class UserConfig {


    @Bean
    public CacheManager cacheManager() {
        log.info("User Type it's been loaded into memory...");
        return new ConcurrentMapCacheManager("userTypes");
    }
}
