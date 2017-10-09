package com.epam.mock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Spring Boot Application class.
 */
@SpringBootApplication
public class Application {

    /**
     * Start point for the application.
     * @param args Application's arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}