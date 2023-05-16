package com.john.springwebfluxudemy.config;

import com.john.springwebfluxudemy.handler.RequestHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicate;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
@RequiredArgsConstructor
public class CalculatorRounterConfig {

    private final RequestHandler requestHandler;

    @Bean
    public RouterFunction<ServerResponse> highLevelRouter() {
        return RouterFunctions.route()
                .path("calculator", this::serverResponseRouterFunction)
                .build();
    }

    private RouterFunction<ServerResponse> serverResponseRouterFunction() {
        final String path = "{firstNo}/{secondNo}";
        return RouterFunctions.route()
                .GET(path, isOperation("+"), requestHandler::additionHandler)
                .GET(path, isOperation("-"), requestHandler::subtractionHandler)
                .GET(path, isOperation("*"), requestHandler::multiplicationHandler)
                .GET(path, isOperation("/"), requestHandler::divisionHandler)
                .GET(path, request -> ServerResponse.badRequest().bodyValue("OP should be +, -, * or /."))
                .build();
    }

    // Getting the operation value from the header.
    private RequestPredicate isOperation(String operation) {
        return RequestPredicates.headers(headers -> operation.equals(headers.asHttpHeaders().toSingleValueMap().get("OP")));
    }
}
