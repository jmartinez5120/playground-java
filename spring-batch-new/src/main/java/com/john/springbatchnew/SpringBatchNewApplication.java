package com.john.springbatchnew;

import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableBatchProcessing
public class SpringBatchNewApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBatchNewApplication.class, args);
    }

}
