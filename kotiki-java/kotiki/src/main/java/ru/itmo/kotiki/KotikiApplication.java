package ru.itmo.kotiki;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"ru.itmo.kotiki.model", "ru.itmo.kotiki.service", "ru.itmo.kotiki.controller"})
public class KotikiApplication {
    public static void main(String[] args) {
        SpringApplication.run(KotikiApplication.class, args);
    }
}