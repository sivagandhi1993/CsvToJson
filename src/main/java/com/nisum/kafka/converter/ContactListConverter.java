package com.nisum.kafka.converter;

import com.nisum.kafka.model.Contact;
import org.springframework.stereotype.Component;

import java.util.function.Function;

import static com.nisum.kafka.util.CONSTANTS.pattern;

@Component
public class ContactListConverter implements Function<String, Contact> {
    @Override
    public Contact apply(String s) {
        String[] x = pattern.split(s,7);
        return new Contact(x[0], x[1], x[2], x[3], x[4], x[5], x[6]);
    }
}
