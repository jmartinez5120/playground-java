package com.john.springdatacache.repository;

import com.john.springdatacache.entity.UserTypeEntity;
import lombok.NonNull;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {

    // OPTION #2: Used to access the already cached data, fetched at the beginning of the App Boot up.
    @Cacheable(value = "initUserTypeCache", key = "#type")
    UserTypeEntity findByType(String type);

    // Erase the cache of a single record.
    @CacheEvict(value = "initUserTypeCache", key = "#type")
    void deleteByType(String type);

    // Creates or updates a record in the database or cache.
    @Override
    @CachePut(value = "initUserTypeCache", key = "#userTypeEntity.type")
    <S extends UserTypeEntity> S save(@NonNull S userTypeEntity);

}
