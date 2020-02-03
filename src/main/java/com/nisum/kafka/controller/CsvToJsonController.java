package com.nisum.kafka.controller;

import com.nisum.kafka.service.CsvToJsonService;
import com.nisum.kafka.util.CONSTANTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

import java.io.File;
import java.util.List;
import java.util.Map;

@RestController
public class CsvToJsonController {

    @Autowired
    private CsvToJsonService csvToJsonService;

    @PostMapping("/convertCsvToJson")
    public List<?> convertCsvToJson(@RequestBody String csvPath) {
        System.out.println(csvPath);
        return csvToJsonService.csvToJsonConverter(csvPath);
    }

   @PostMapping("/convertCsvToJsonWithoutPojo")
    public List<?> convertCsvToJsonWithoutPojo(@RequestBody String csvPath) {
        System.out.println(csvPath);
        return csvToJsonService.convertCsvToJsonWithoutPojo(csvPath);
    }

    @PostMapping("/convertCsvToJsonWithoutPojoFlux")
    public Flux<List<Map<String, String>>> convertCsvToJsonUsingFlux(@RequestBody String csvPath) {
        System.out.println(csvPath);
        return csvToJsonService.convertCsvToJsonWithoutPojowithflux(csvPath);
    }
}
