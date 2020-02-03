package com.nisum.kafka.service;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.nisum.kafka.converter.*;
import com.nisum.kafka.util.CONSTANTS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import sun.applet.Main;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CsvToJsonService {

    @Autowired
    private ChatConverter chatConverter;
    @Autowired
    private ContactListConverter contactListConverter;
    @Autowired
    private SalesConverter salesConverter;
    @Autowired
    GenericConverter genericConverter;
    @Autowired
    MainConverter mainConverter;

    public List<?> csvToJsonConverter(String s) {
        List<?> conversion = new ArrayList();
        if (s.contains("chat")) {
            try (BufferedReader in = new BufferedReader((new FileReader(s)));) {
                conversion = in.lines().skip(2).filter(t -> !t.isEmpty()).map(chatConverter).collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conversion;
        } else if (s.contains("contact")) {
            try (BufferedReader in = new BufferedReader((new FileReader(s)));) {
                conversion = in.lines().skip(2).filter(t -> !t.isEmpty()).map(contactListConverter).collect(Collectors.toList());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conversion;
        } else if (s.contains("Sales")) {
            try (BufferedReader in = new BufferedReader((new FileReader(s)));) {
                conversion = in.lines().skip(2).filter(t -> !t.isEmpty()).map(salesConverter).collect(Collectors.toList());
                return conversion;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return conversion;
        }
        return conversion;
    }

    public Flux<List<Map<String, String>>> convertCsvToJsonWithoutPojowithflux(String csvPath) {
        Path path = Paths.get(csvPath);
        return genericConverter.apply(path);
    }

    public List<?> convertCsvToJsonWithoutPojo(String csvPath) {
        return mainConverter.apply(csvPath);
    }

}
