package com.john.springwebfluxudemy.exception;

import lombok.Getter;

@Getter
public class RequestValidationException extends RuntimeException{

    private static final String MSG = "allowed range is 10 to 20";
    private final int errorCode = 400;
    private final int input;

    public RequestValidationException(int input) {
        super(MSG);
        this.input = input;
    }
}
