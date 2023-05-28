package com.john.udemy.reactivejava.sec01;

import reactor.core.publisher.Mono;

public class Lec02MonoJust {

    public static void main(String[] args) {

        // Publisher
        Mono<Integer> mono = Mono.just(1);

        // #1 Rule of Reactive - nothing happens until you subscribe.
        System.out.println(mono);
        // Subscriber
        mono.subscribe(i -> System.out.println("Received: " + i));
    }
}
