package com.john.springwebfluxudemy.handler;

import com.john.springwebfluxudemy.exception.RequestValidationException;
import com.john.springwebfluxudemy.model.MultiplyRequest;
import com.john.springwebfluxudemy.model.RequestFailedValidationResponse;
import com.john.springwebfluxudemy.model.Response;
import com.john.springwebfluxudemy.service.ReactiveMathService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class RequestHandler {

    private final ReactiveMathService reactiveMathService;

    public Mono<ServerResponse> squareHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        // This is not executing anything, because we haven't subscribe for the pipeline.
        Mono<Response> responseMono = reactiveMathService.findSquare(input);
        // Here we subscribe to the pipeline, so it starts executing the reactive code.
        return ServerResponse.ok().body(responseMono, Response.class);
    }

    // This method returns a Mono, even tho the multiplication table returns a Flux, the reason is that
    // the ServerResponse is only one, but inside it will have the Flux/multiple values.
    public Mono<ServerResponse> tableHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        // This is not executing anything, because we haven't subscribe for the pipeline.
        Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
        // Here we subscribe to the pipeline, so it starts executing the reactive code.
        return ServerResponse.ok().body(responseFlux, Response.class);
    }

    // This method returns a Mono, even tho the multiplication table returns a Flux, the reason is that
    // the ServerResponse is only one, but inside it will have the Flux/multiple values.
    public Mono<ServerResponse> tableStreamHandler(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        // This is not executing anything, because we haven't subscribed for the pipeline.
        Flux<Response> responseFlux = reactiveMathService.multiplicationTable(input);
        // Here we subscribe to the pipeline, so it starts executing the reactive code.
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_EVENT_STREAM) // To create a stream of the response.
                .body(responseFlux, Response.class);
    }

    public Mono<ServerResponse> multiplyHandler(ServerRequest serverRequest) {
        // Body to Mono will retrieve the body from the server request.
        Mono<MultiplyRequest> multiplyRequestMono = serverRequest.bodyToMono(MultiplyRequest.class);
        // This is not executing anything, because we haven't subscribed for the pipeline.
        Mono<Response> responseMono = reactiveMathService.multiply(multiplyRequestMono);
        // Here we subscribe to the pipeline, so it starts executing the reactive code.
        return ServerResponse.ok()
                .body(responseMono, Response.class);
    }

    public Mono<ServerResponse> squareHandlerWithValidations(ServerRequest serverRequest) {
        int input = Integer.parseInt(serverRequest.pathVariable("input"));
        if (input < 10 || input > 20) {
            // Option #1:
//            RequestFailedValidationResponse requestFailedValidationResponse = new RequestFailedValidationResponse();
            // Since this is not a publisher type, this is the raw object, we use bodyValue.
//            return ServerResponse.badRequest().bodyValue(requestFailedValidationResponse);

            // Option #2: PREFERRED
            return Mono.error(new RequestValidationException(input)); // Emitting the error signal.
        }
        // This is not executing anything, because we haven't subscribed for the pipeline.
        Mono<Response> responseMono = reactiveMathService.findSquare(input);
        // Here we subscribe to the pipeline, so it starts executing the reactive code.
        return ServerResponse.ok().body(responseMono, Response.class);
    }

}
