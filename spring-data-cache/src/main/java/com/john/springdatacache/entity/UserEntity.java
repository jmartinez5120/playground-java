package com.john.springdatacache.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "users", schema = "application_enum_cache")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_type_id", referencedColumnName = "id")
    private UserTypeEntity userType;

    private String firstName;

    private String middleName;

    private String lastName;

    private String createdBy;

    private String lastUpdatedBy;

    private LocalDateTime createdDatetime;

    private LocalDateTime lastUpdatedDatetime;
}
