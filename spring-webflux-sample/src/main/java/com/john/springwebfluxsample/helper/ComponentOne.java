package com.john.springwebfluxsample.helper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ComponentOne {

    private final ComponentTwo componentTwo;

    @Autowired
    public ComponentOne(ComponentTwo componentTwo) {
        this.componentTwo = componentTwo;
    }

    public String concatString(String request) throws InterruptedException {
        System.out.println("This is component 1, going to sleep...");
        Thread.sleep(10000);
        System.out.println("Component 1 is up and awake!");
        return request + " request in thread -> " + componentTwo.concatThread(request);
    }
}
