package com.john.springwebfluxudemy.config;

import com.john.springwebfluxudemy.exception.RequestValidationException;
import com.john.springwebfluxudemy.handler.RequestHandler;
import com.john.springwebfluxudemy.model.RequestFailedValidationResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.server.*;
import reactor.core.publisher.Mono;

import java.util.function.BiFunction;

@Configuration
@RequiredArgsConstructor
public class RouterConfig {

    private final RequestHandler requestHandler;

    // This parent router function will keep from the repeating path in the Server request from been coded on all
    // the available functions.
    @Bean
    public RouterFunction<ServerResponse> functionPath() {
        return RouterFunctions.route()
                .path("router", this::serverResponseRouterFunction)
                .path("math", this::serverResponseRouterMathFunction)
                .build();
    }

    // This server response router function routes all the requests coming to a specific URL,
    // to the corresponding component.
    // <<IMPORTANT>> You can have multiple of the router functions, to keep things organized.
    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        return RouterFunctions.route()
//                .GET("square/{input}", requestHandler::squareHandler)
                // Instead of the above, we can use Request predicates, which will take in consideration the input values.
                // This is saying that the input values will have a 1 and then another number, so it will validate 10-19.
                // We added the OR condition, to add the 20 in the pattern.
                .GET("square/{input}", RequestPredicates.path("*/1?").or(RequestPredicates.path("*/20")), requestHandler::squareHandler)
                // If the above condition doesn't match for "square/{input} request predicate values, then if will throw
                // a bad request.
                .GET("square/{input}", request -> ServerResponse.badRequest().bodyValue("Only 10-20 allowed"))
                .GET("table/{input}", requestHandler::tableHandler)
                .GET("table/stream/{input}", requestHandler::tableStreamHandler)
                .POST("multiply", requestHandler::multiplyHandler)
                // Another way to validate, other than RequestPredicates, is with the below.
                .GET("square/{input}/validation", requestHandler::squareHandlerWithValidations)
                // If any of the above functions throws an exception of type RequestValidationException.class, then
                // it will execute the exceptionHandler function.
                .onError(RequestValidationException.class, exceptionHandler())
                .build();
    }

    // Assigment - Math operation
    private RouterFunction<ServerResponse> serverResponseRouterMathFunction() {
        return RouterFunctions.route()
                .GET("operation/{first}/{second}", requestHandler::mathOperationHandler)
                .build();
    }

    // NOTE: We should build this in a Controller Advice, but for example purposes is here.
    private BiFunction<Throwable, ServerRequest, Mono<ServerResponse>> exceptionHandler() {
        return (err, req) -> {
            RequestValidationException exception = (RequestValidationException) err;
            RequestFailedValidationResponse response = new RequestFailedValidationResponse();
            response.setInput(exception.getInput());
            response.setMessage(exception.getMessage());
            response.setErrorCode(exception.getErrorCode());
            // Not a publisher type, so we use bodyValue.
            return ServerResponse.badRequest().bodyValue(response);
        };
    }
}
