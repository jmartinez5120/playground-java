package com.john.udemy.reactivejava.sec01;

import java.util.stream.Stream;

// Streams are lazy loaded. This means that the operations will be only executed when there
// is a reason to call the operation. In this case, a terminal operation is called to call the
// stream method.
public class Lec01Stream {

    public static void main(String[] args) {
        Stream<Integer> stream = Stream.of(1)
                .map(i -> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                    return i * 2;
                });
        // SYS OUT will print really fast because we are not calling the terminal operator
        // of the stream.
        System.out.println(stream);
        // Here we are calling the terminal operator of the stream, therefor the operation
        // executes.
        stream.forEach(System.out::println);
    }
}
