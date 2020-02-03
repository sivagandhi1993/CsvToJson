package com.nisum.kafka.converter;

import com.nisum.kafka.util.CONSTANTS;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.BaseStream;

@Component
public class GenericConverter implements Function<Path, Flux<List<Map<String, String>>>> {
    @Override
    public Flux<List<Map<String, String>>> apply(Path path) {
        final Runtime runtime = Runtime.getRuntime();
        Flux<String> fluxContent = Flux.using(() -> Files.lines(path),
                Flux::fromStream,
                BaseStream::close);
        Flux<List<Map<String, String>>> fluxReturn = Flux.just();
        List<Map<String, String>> list = new ArrayList();
        fluxContent.take(1).doOnNext(hd -> {
            List<String> headers = Arrays.asList(hd.split(","));
            fluxContent.skip(1).filter(t -> !t.isEmpty()).parallel()
                    .runOn(Schedulers.elastic()).map(o -> {
                List<String> x = Arrays.asList(CONSTANTS.pattern.split(o, headers.size()));
                Map<String, String> obj = new LinkedHashMap<>();
                for (int i = 0; i < headers.size(); i++) {
                    obj.put("\"" + headers.get(i) + "\"", "\"" + x.get(i) + "\"");
                }
                list.add(obj);
                return list;
            }).subscribe(s -> {
                try {
                    Files.write(Paths.get("C:/Users/sgandhi/files.txt"), s.toString().replace("=", ":").getBytes());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });

        }).subscribe(System.out::println);
        return fluxReturn;

    }
}
