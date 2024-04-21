package com.john.springdatacache.repository;

import com.john.springdatacache.entity.UserType;
import com.john.springdatacache.entity.UserTypeEntity;

import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {

    // NOTE: Used to fetch the data and cache it as it is fetched from the DB with the API requests.
//    UserTypeEntity findByType(UserType type);


    // NOTE: It is used to load all the values from the User Type table on the PostConstruct method.
    @Override
    @Cacheable("initUserTypeCache")
    List<UserTypeEntity> findAll();

    // NOTE: Used to access the already cached data, fetched at the beginning of the App Boot up.
    @Cacheable(value = "initUserTypeCache", key = "#type")
    UserTypeEntity findByType(UserType type);
}
