package com.nisum.kafka.converter;

import com.nisum.kafka.model.chat;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.nisum.kafka.util.CONSTANTS.*;

@Component
public class ChatConverter implements Function<String, chat> {

    @Override
    public chat apply(String s) {

        String[] x = pattern.split(s, 2);
        String[] y = rightBracketPattern.split(x[1], 2);
        String[] z;
        if (y.length < 2) {
            z = arrowLeftPattern.split(x[1]);
            if (z.length < 2) {
                return new chat(x[0], y[0], "No Username", z[0]);
            } else {
                return new chat(x[0], "No Username Available", z[0], z[1]);
            }

        } else {
            z = arrowLeftPattern.split(y[1]);
            if (z.length < 2) {
                return new chat(x[0], y[0], "No Username", z[0]);
            } else {
                return new chat(x[0], y[0], z[0], z[1]);
            }
        }
    }
}
