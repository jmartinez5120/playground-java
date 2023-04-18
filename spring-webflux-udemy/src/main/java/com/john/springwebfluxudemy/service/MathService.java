package com.john.springwebfluxudemy.service;

import com.john.springwebfluxudemy.model.Response;
import com.john.springwebfluxudemy.service.helper.SleepUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Log4j2
public class MathService {

    public Response findSquare(int input) {
        return new Response(input * input);
    }

    public List<Response> multiplicationTable(int input) {
        return IntStream.rangeClosed( 1, 10)
                .peek(i -> SleepUtil.sleepSeconds(1))
                .peek(i -> log.info("math-service processing : {} ", i))
                .mapToObj(i -> new Response(i * input))
                .collect(Collectors.toList());
    }
}

