package com.john.udemy.reactivejava.utils;

import com.github.javafaker.Faker;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.function.Consumer;

// Just contains some of the most common prints in the lecture, so we don't have to repeat the code.
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Utilities {

    public static final Faker FAKER = Faker.instance();

    public static Consumer<Object> onNext() {
        return o -> System.out.println("Received : " + o);
    }

    public static Consumer<Throwable> onError() {
        return e -> System.out.println("ERROR: " + e.getMessage());
    }

    public static Runnable onComplete() {
        return () -> System.out.println("Completed.");
    }

    public static Faker faker() {
        return FAKER;
    }

    public static void sleepSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
