package com.tinqinacademy.commentsservice.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.tinqinacademy.commentsservice.persistence.repository")
@EntityScan(basePackages = "com.tinqinacademy.commentsservice.persistence.model.entity")
@ComponentScan(basePackages = "com.tinqinacademy.commentsservice")
public class CommentsServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(CommentsServiceApplication.class, args);
    }

}
