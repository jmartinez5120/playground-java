package com.john.springwebfluxtest;

import org.reactivestreams.Publisher;
import org.springframework.core.Ordered;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpResponseDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;
import reactor.util.annotation.NonNull;

@Component
public class AfterCompletionFilter implements WebFilter, Ordered {

    @NonNull
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        // Decorate the response to intercept the write operation completion
        ServerHttpResponseDecorator decoratedResponse = new ServerHttpResponseDecorator(exchange.getResponse()) {
            @Override
            @NonNull
            public Mono<Void> writeWith(@NonNull Publisher<? extends DataBuffer> body) {
                // Wrap the original writeWith operation with additional behavior
                return super.writeWith(body).then(Mono.fromRunnable(AfterCompletionFilter.this::executeAfterCompletionRoutine));
            }
        };


        // Continue the filter chain with the decorated response
        return chain.filter(exchange.mutate().response(decoratedResponse).build());
    }

    private void executeAfterCompletionRoutine() {
        try {
            System.out.println("Now Sleeping for 3 seconds");
            Thread.sleep(3000);
            System.out.println("Woke up");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // Your post-completion logic here
        System.out.println("Routine executed after response completion.");
    }

    @Override
    public int getOrder() {
        // Adjust the order as necessary
        return Ordered.LOWEST_PRECEDENCE;
    }
}
