package com.webapp.endavacourseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Level;
import java.util.logging.Logger;

@SpringBootApplication
public class EndavaCourseProjectApplication {

    private static final Logger logger = Logger.getLogger(String.valueOf(EndavaCourseProjectApplication.class));
    public static void main(String[] args) {

        logger.setLevel(Level.ALL);
        SpringApplication.run(EndavaCourseProjectApplication.class, args);
    }

}
