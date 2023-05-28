package com.john.udemy.reactivejava.sec01;

import com.john.udemy.reactivejava.utils.Utilities;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

public class Lec08MonoFromRunnable {

    public static void main(String[] args) {
        // Doesn't give any value.
        // This is only used when you want to be notified when the a task is completed.
//        Runnable runnable = () -> System.out.println("John");

        Mono.fromRunnable(timeConsumingProcess())
                .subscribe(Utilities.onNext(),
                        Utilities.onError(),
                        Utilities.onComplete()); // You can add a business logic on complete.

        // To check if the Runnable blocks. IT DOES!
        System.out.println(LocalDateTime.now() + " - Reached below the Runnable.");

    }

    private static Runnable timeConsumingProcess() {
        return () -> {
            Utilities.sleepSeconds(3);
            System.out.println(LocalDateTime.now() + " - Operation completed.");
        };
    }


}
