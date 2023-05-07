package com.john.springwebfluxudemy.exceptionhandler;

import com.john.springwebfluxudemy.exception.RequestValidationException;
import com.john.springwebfluxudemy.model.RequestFailedValidationResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class RequestValidationHandler {

    @ExceptionHandler(RequestValidationException.class)
    public ResponseEntity<RequestFailedValidationResponse> handleException(RequestValidationException exception) {
        RequestFailedValidationResponse response = new RequestFailedValidationResponse();
        response.setErrorCode(exception.getErrorCode());
        response.setInput(exception.getInput());
        response.setMessage(exception.getMessage());
        return ResponseEntity.badRequest().body(response);
    }
}
