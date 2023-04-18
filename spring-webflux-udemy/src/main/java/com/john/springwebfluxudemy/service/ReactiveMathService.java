package com.john.springwebfluxudemy.service;

import com.john.springwebfluxudemy.model.Response;
import com.john.springwebfluxudemy.service.helper.SleepUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
@Log4j2
public class ReactiveMathService {

    public Mono<Response> findSquare(int input) {
        return Mono.fromSupplier(() -> input * input)
                .map(Response::new);
    }

    public Flux<Response> multiplicationTable(int input) {
        return Flux.range(1, 10)
                .doOnNext(i -> SleepUtil.sleepSeconds(1))
                .doOnNext(i -> log.info("reactive-math-service processing: {}", i))
                .map(i -> new Response(i * input));
    }
}
