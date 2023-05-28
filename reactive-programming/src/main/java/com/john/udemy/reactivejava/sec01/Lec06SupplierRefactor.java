package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

import java.time.LocalDateTime;

public class Lec06SupplierRefactor {

    public static void main(String[] args) {
        getUserNamePublisher();
        // <<IMPORTANT - BLOCKS>> Only when we subscribe to the publisher, is when the Thread gets blocked.
        getUserNamePublisher()
                // <<IMPORTANT>> This is added so that this call doesn't block the thread. This is going to be explained
                // in a later lecture.
                .subscribeOn(Schedulers.boundedElastic())
                .subscribe(Utilities.onNext());
        getUserNamePublisher();

        // This is only added, so that the app doesn't exits before finishes printing the output of
        // the getUserNamePublisher() method. In real life, this will not be needed.
        Utilities.sleepSeconds(4);

        // Another way to block the Thread is to use .block() method, instead of providing a subscriber.
        // This is not supposed to be used in a real-life app, this would break the reactive Thread.
        getUserNamePublisher().subscribeOn(Schedulers.boundedElastic()).block();

    }

    // Refactoring the supplier from previous lecture, to handle the business logic inside the supplier.
    // Even if is called multiple times, the thread is not block.
    // <<IMPORTANT>> This method BUILDS the pipeline, however doesn't execute it.
    private static Mono<String> getUserNamePublisher() {
        System.out.println(LocalDateTime.now() + " - Entered the getUserNamePublisher() method.");
        return Mono.fromSupplier(() -> {
                    // By moving the business logic inside the Pipeline, then it gets executed Lazy.
                    System.out.println("Generate name ...");
                    Utilities.sleepSeconds(3);
                    return "Loaded Timestamp: " + LocalDateTime.now() + " - Full Name: " + Utilities.faker().name().fullName();
                }
        ).map(String::toUpperCase);
    }
}
