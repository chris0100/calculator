package org.system.springcloud.msvc.arithmetic.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.system.springcloud.msvc.arithmetic.clients.ramdon.RandomStringClient;
import org.system.springcloud.msvc.arithmetic.entities.dtos.RequestRandomDto;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static org.system.springcloud.msvc.arithmetic.clients.ramdon.RandomStringClient.CONTENT_TYPE;


@Component
public class Functions {
    @Autowired
    private RandomStringClient randomStringClient;

    @Autowired
    private RequestRandomDto requestRandomDto;

    public BiFunction<Double,Double,String> addition = (a, b) -> String.valueOf(a + b);
    public BiFunction<Double,Double,String> subtraction = (a, b) -> String.valueOf(a - b);
    public BiFunction<Double,Double,String> multiplication = (a, b) -> String.valueOf(a * b);
    public BiFunction<Double,Double,String> division = (a, b) -> String.valueOf(a / b);
    public Function<Double,String> squareRoot = a -> String.valueOf(Math.sqrt(a));
    public Supplier<String> randomString = () -> randomStringClient.generateRandomStrings(CONTENT_TYPE, requestRandomDto).getResult().getRandom().getData().toString();
}
