package com.john.udemy.reactivejava.sec02;

import reactor.core.publisher.Flux;

public class Lec02MultipleSubscribers {


    public static void main(String[] args) {

        Flux<Integer> flux = Flux.just(1, 2, 3, 4);

        flux.subscribe(i -> System.out.println("Subs #1:" + i));
        flux.subscribe(i -> System.out.println("Subs #2:" + i));
        Flux<Integer> evenFlux = flux.filter(i -> i % 2 == 0);
        evenFlux.subscribe(i -> System.out.println("Even Number: " + i));


    }
}
