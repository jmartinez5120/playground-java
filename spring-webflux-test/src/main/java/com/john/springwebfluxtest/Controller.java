package com.john.springwebfluxtest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class Controller {

    @GetMapping("/")
    public Mono<String> gerResource() {
        return Mono.just("Something here");

    }

}
