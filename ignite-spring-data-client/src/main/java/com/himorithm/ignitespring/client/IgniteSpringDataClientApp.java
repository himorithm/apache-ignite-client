package com.himorithm.ignitespring.client;

import org.apache.ignite.springdata20.repository.config.EnableIgniteRepositories;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableIgniteRepositories
public class IgniteSpringDataClientApp {
    public static void main(String[] args) {
        SpringApplication.run(IgniteSpringDataClientApp.class, args);
    }

}
