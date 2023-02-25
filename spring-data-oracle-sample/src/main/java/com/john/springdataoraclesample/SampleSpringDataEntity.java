package com.john.springdataoraclesample;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Setter
@Getter
@Entity
@Table(name = "sample_spring_data")
public class SampleSpringDataEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "today_date")
    private LocalDate todayDate;

    @Column(name = "today_datetime")
    private LocalDateTime todayDatetime;

    @Column(name = "is_active")
    private boolean isActive;

    @Column(name = "random_number")
    private int randomNumber;
}
