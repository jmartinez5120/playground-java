package com.john.springdataoraclesample;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SampleSpringDataRepository extends JpaRepository<SampleSpringDataEntity, Long> {
}
