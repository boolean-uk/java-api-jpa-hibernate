package com.booleanuk.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource("file:src/main/java/com/booleanuk/api/config.txt")
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }
}
