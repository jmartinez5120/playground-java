package com.john.springwebfluxsample.controller;

import com.john.springwebfluxsample.helper.ComponentOne;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
public class FluxAndMonoController {

    private final ComponentOne componentOne;

    @Autowired
    public FluxAndMonoController(ComponentOne componentOne) {
        this.componentOne = componentOne;
    }

    @GetMapping("/flux")
    public Flux<Integer> getFlux() {
        return Flux.just(1, 2, 3, 4)
                .delayElements(Duration.ofSeconds(10))
                .log();
    }

    /*
     * It will keep sending events to the clients every second, without stopping.
     * If multiple clients connect to this endpoint, then each will receive the values individually.
     * If the app shutdown, then it will send a signal to the client of cancel gracefully.
     */
    @GetMapping(value = "/fluxStream", produces = MediaType.APPLICATION_STREAM_JSON_VALUE)
    public Flux<Long> getFluxStream() {
        return Flux.interval(Duration.ofSeconds(1)) // Will send invents without stopping.
//                .delayElements(Duration.ofMillis(100))
                .log();
    }

    /*
     * Will handle requests with a single resource in a non-blocking way.
     */
    @GetMapping("/mono")
    public Mono<Integer> getMono() {
        return Mono.just(1)
//                .delayElements(Duration.ofSeconds(1))
                .log();
    }

    @PostMapping("/components")
    public Mono<String> fluxResource(@RequestBody String request) throws InterruptedException {
        return Mono.just(componentOne.concatString(request)).log();
    }
}
