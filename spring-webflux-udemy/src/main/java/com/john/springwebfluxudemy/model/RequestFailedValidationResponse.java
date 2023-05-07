package com.john.springwebfluxudemy.model;

import lombok.Data;

@Data
public class RequestFailedValidationResponse {

    private int errorCode;
    private int input;
    private String message;
}
