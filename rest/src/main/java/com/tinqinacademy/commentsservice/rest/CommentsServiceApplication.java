package com.tinqinacademy.commentsservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.tinqinacademy.commentsservice")
public class CommentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsServiceApplication.class, args);
    }

}
