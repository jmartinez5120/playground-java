package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;

public class Lec03MonoSubscribe {

    public static void main(String[] args) {

        Mono<String> mono = Mono.just("ball");

        // If nothing is provided, even if you subscribe, nothing is going to happen.
        mono.subscribe();

        // In this case, we are providing an onNext behavior to the stream, therefor the
        // mono is executed.
        mono.subscribe(
                // onNext behavior
//                input -> System.out.println(input),
                Utilities.onNext(),
                // onError
//                err -> System.out.println(err.getMessage()),
                Utilities.onError(),
                // onComplete
//                () -> System.out.println("Completed")
                Utilities.onComplete()
        );
    }
}
