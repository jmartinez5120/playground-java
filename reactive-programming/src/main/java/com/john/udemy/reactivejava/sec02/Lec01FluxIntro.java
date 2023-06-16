package com.john.udemy.reactivejava.sec02;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Flux;

public class Lec01FluxIntro {


    public static void main(String[] args) {

        Flux<Object> flux = Flux.just(1, 2, 3, 4, "a", Utilities.faker().name().fullName());
        flux.subscribe(
                Utilities.onNext(),
                Utilities.onError(),
                Utilities.onComplete());


    }
}
