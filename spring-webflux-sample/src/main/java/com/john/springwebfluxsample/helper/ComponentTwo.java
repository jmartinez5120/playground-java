package com.john.springwebfluxsample.helper;

import org.springframework.stereotype.Component;

@Component
public class ComponentTwo {

    public String concatThread(String request) throws InterruptedException {
        System.out.println("This is component 2, going to sleep...");
        Thread.sleep(200);
        System.out.println("Component 2 is up and awake!");
        return request + Thread.currentThread().getName();
    }
}
