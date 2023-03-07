package com.john.springwebfluxsample.fluxandmonoplayground;

import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

public class FluxAndMonoTest {

    @Test
    void fluxTest() {
        /* WHEN COMPLETES WITHOUT ISSUES
         * 07:04:34.917 [main] DEBUG reactor.util.Loggers - Using Slf4j logging framework
         * 07:04:34.926 [main] INFO reactor.Flux.Array.1 - | onSubscribe([Synchronous Fuseable] FluxArray.ArraySubscription)
         * 07:04:34.928 [main] INFO reactor.Flux.Array.1 - | request(unbounded)
         * 07:04:34.928 [main] INFO reactor.Flux.Array.1 - | onNext(Spring)
         * Spring
         * 07:04:34.928 [main] INFO reactor.Flux.Array.1 - | onNext(Spring Boot)
         * Spring Boot
         * 07:04:34.928 [main] INFO reactor.Flux.Array.1 - | onNext(Reactive Spring)
         * Reactive Spring
         * 07:04:34.928 [main] INFO reactor.Flux.Array.1 - | onComplete()
         */
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                // For testing purposes, I am throwing an Exception, to see the print of the Exception below.
//                .concatWith(Flux.error(new RuntimeException("Exception is thrown")))
                .concatWith(Flux.just("After Error"))
                .log();
        // IF an exception is thrown, then the thrid parameter "Completed" is never going to be printed out.
        stringFlux.subscribe(System.out::println, System.err::println, () -> System.out.println("Completed"));
    }

    @Test
    void fluxTestElements_WithoutErrors() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
                .verifyComplete(); // If you don't call this line, then is not going to start the flow of elements from the Flux. Will start the subscriber flow.
    }

    @Test
    void fluxTestElements_WithErrors() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .concatWith(Flux.error(new RuntimeException("Exception Occurred")))
                .log();
        StepVerifier.create(stringFlux)
                .expectNext("Spring")
                .expectNext("Spring Boot")
                .expectNext("Reactive Spring")
//                .expectError(RuntimeException.class) // You can't have expectError and expectErrorMessage together.
                .expectErrorMessage("Exception Occurred")
                .verify(); // Will start the subscriber flow.
    }

    @Test
    void fluxTestElements_NumberOfElements() {
        Flux<String> stringFlux = Flux.just("Spring", "Spring Boot", "Reactive Spring")
                .log();
        StepVerifier.create(stringFlux)
                .expectNextCount(3)
                .verifyComplete(); // Will start the subscriber flow.
    }

    @Test
    void monoTest() {
        Mono<String> monoValue = Mono.just("Spring Mono");
        StepVerifier.create(monoValue.log())
                .expectNext("Spring Mono")
                .verifyComplete();
    }

    @Test
    void monoTest_Error() {
        StepVerifier.create(Mono.error(new RuntimeException("Exception Occurred")).log())
                .expectError(RuntimeException.class)
                .verify();
    }
}
