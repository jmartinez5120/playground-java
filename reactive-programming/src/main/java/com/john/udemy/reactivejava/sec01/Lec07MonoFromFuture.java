package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;

import java.util.concurrent.CompletableFuture;

public class Lec07MonoFromFuture {

    public static void main(String[] args) {
        // A Subscriber can call a CompletableFuture method.
        Mono.fromFuture(getNameFuture())
                .subscribe(Utilities.onNext());

        // Just for showing the results.
        Utilities.sleepSeconds(1);

    }

    private static CompletableFuture<String> getNameFuture() {
        return CompletableFuture.supplyAsync(() -> Utilities.faker().name().fullName());
    }
}
