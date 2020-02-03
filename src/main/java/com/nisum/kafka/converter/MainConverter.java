package com.nisum.kafka.converter;

import com.nisum.kafka.util.CONSTANTS;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;
import java.util.function.Function;
@Component
public class MainConverter implements Function<String, List<?>>{
    @Override
    public List<?> apply(String s) {
        List<Map<String,String>> list = new ArrayList<>();
        try (BufferedReader in = new BufferedReader((new FileReader(s)));) {
            in.lines().findFirst().ifPresent(t -> {
                List<String> fieldNames = Arrays.asList(t.split(","));
                System.out.println(fieldNames.size());

                in.lines().skip(2).filter(p -> !p.isEmpty()).forEach(o -> {
                    List<String> x = Arrays.asList(CONSTANTS.pattern.split(o, fieldNames.size()));
                    Map<String, String> obj = new LinkedHashMap<>();
                    for (int i = 0; i < fieldNames.size(); i++) {
                        obj.put(fieldNames.get(i), x.get(i));
                    }
                    list.add(obj);
                });
            });
            return list;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }
}
