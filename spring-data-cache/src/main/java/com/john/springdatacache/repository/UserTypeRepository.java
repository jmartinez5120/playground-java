package com.john.springdatacache.repository;

import com.john.springdatacache.entity.UserTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserTypeRepository extends JpaRepository<UserTypeEntity, Long> {

    UserTypeEntity findByType(String type);
}
