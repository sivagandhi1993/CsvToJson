package com.nisum.kafka.converter;

import com.nisum.kafka.model.Sales;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.nisum.kafka.util.CONSTANTS.pattern;

@Component
public class SalesConverter implements Function<String, Sales> {
    @Override
    public Sales apply(String s) {
        String[] x = pattern.split(s, 14);
        return new Sales(x[0], x[1], x[2], x[3], x[4], x[5], x[6], x[7], x[8], x[9], x[10], x[11], x[12], x[13]);
    }
}
