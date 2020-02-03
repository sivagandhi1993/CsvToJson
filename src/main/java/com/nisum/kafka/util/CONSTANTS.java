package com.nisum.kafka.util;

import lombok.Data;

import java.util.regex.Pattern;

@Data
public class CONSTANTS {
    public static final Pattern pattern = Pattern.compile(",");
    public static final Pattern rightBracketPattern = Pattern.compile("]");
    public static final Pattern arrowLeftPattern = Pattern.compile(">");
    public static final String chatCsvPath = "C:/Users/sgandhi/Downloads/chat.csv";
    public static final String contactCsvPath = "C:/Users/sgandhi/Downloads/contact.csv";
    public static final String salesCsvPath = "C:/Users/sgandhi/Downloads/Sales.csv";
}
