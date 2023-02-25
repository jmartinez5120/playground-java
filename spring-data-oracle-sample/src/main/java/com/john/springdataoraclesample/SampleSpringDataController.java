package com.john.springdataoraclesample;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class SampleSpringDataController {

    private final SampleSpringDataRepository sampleSpringDataRepository;

    @GetMapping("/user_data")
    public ResponseEntity<List<SampleSpringDataEntity>> getUserData() {
        List<SampleSpringDataEntity> response = sampleSpringDataRepository.findAll();
        return response.isEmpty() ? new ResponseEntity<>(HttpStatus.NO_CONTENT) : ResponseEntity.ok(response);
    }
}
