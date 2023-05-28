package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.concurrent.Callable;
import java.util.function.Supplier;

public class Lec05MonoFromSupplier {

    public static void main(String[] args) {

        // We should only use this when we have data already.
//        Mono<String> mono = Mono.just(getUserName());

        // If we are calling a method that doesn't return a Mono, then we have to call it
        // with a supplier. The supplier is Lazy Loaded as well, so the method won't be
        // called until we provide a Subscriber.
        Mono<String> mono = Mono.fromSupplier(Lec05MonoFromSupplier::getUserName);
        mono.subscribe(
                Utilities.onNext()
        );


        // Invoke from Supplier
        Supplier<String> stringSupplier = Lec05MonoFromSupplier::getUserName;
        Mono<String> monoSupplier = Mono.fromSupplier(stringSupplier);
        monoSupplier.subscribe(
                Utilities.onNext()
        );

        // Invoke using a Callable.
        Callable<String> stringCallable = Lec05MonoFromSupplier::getUserName;
        Mono.fromCallable(stringCallable)
                .subscribe(
                        Utilities.onNext()
                );

    }

    // <<LOOK>> This is not a Mono, it is just returning a String.
    private static String getUserName() {
        System.out.println("Generate name ...");
        return "Loaded Timestamp: " + LocalDateTime.now() + " - Full Name: " + Utilities.faker().name().fullName();
    }
}
