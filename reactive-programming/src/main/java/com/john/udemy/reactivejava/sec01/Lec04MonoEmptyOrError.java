package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;

public class Lec04MonoEmptyOrError {

    public static void main(String[] args) {

        // This is the Subscriber.
        userRepository(1)
                .subscribe(
                        Utilities.onNext(),
                        Utilities.onError(),
                        Utilities.onComplete()
                );
    }

    // This guy acts as the Publisher.
    private static Mono<String> userRepository(int userId) {
        if (userId == 1) {
            return Mono.just(Utilities.faker().name().firstName());
        } else if (userId == 2) {
            // In Reactor, instead of returning null when not found, you pass empty().
            return Mono.empty();
        } else {
            return Mono.error(new RuntimeException("Not in the allowed User Id range."));
        }
    }
}
