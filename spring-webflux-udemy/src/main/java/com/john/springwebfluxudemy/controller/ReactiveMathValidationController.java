package com.john.springwebfluxudemy.controller;

import com.john.springwebfluxudemy.exception.RequestValidationException;
import com.john.springwebfluxudemy.model.Response;
import com.john.springwebfluxudemy.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("reactive-math")
@RequiredArgsConstructor
public class ReactiveMathValidationController {

    private final ReactiveMathService reactiveMathService;

    // Here you are making a validation blocking the reactive pipeline.
    @GetMapping("square/{input}/throw")
    public Mono<Response> findSquare(@PathVariable int input) {
        if (input < 10 || input > 20) {
            throw new RequestValidationException(input);
        }
        return reactiveMathService.findSquare(input);
    }

    // Here you are making validations to the input inside the Event Loop
    @GetMapping("square/{input}/mono-error")
    public Mono<Response> monoError(@PathVariable int input) {

        return Mono.just(input).handle(((integer, responseSynchronousSink) -> {
            if (integer >= 10 && integer <=20)
                responseSynchronousSink.next(integer);
            else
                // Throws the error signal, the reactive way.
                responseSynchronousSink.error(new RequestValidationException(integer));
        }))
                .cast(Integer.class)
                .flatMap(reactiveMathService::findSquare);

    }

    // A way to handle validations in the reactive pipeline.
    @GetMapping("square/{input}/assignment")
    public Mono<ResponseEntity<Response>> assignment(@PathVariable int input) {
        return Mono.just(input)
                .filter(i -> i>=10 && i <= 20)
                .flatMap(reactiveMathService::findSquare)
                .map(ResponseEntity::ok)
                .defaultIfEmpty(ResponseEntity.badRequest().build());
    }
}
