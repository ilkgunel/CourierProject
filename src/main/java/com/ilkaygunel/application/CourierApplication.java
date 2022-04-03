package com.ilkaygunel.application;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan({"com.ilkaygunel.*"})
public class CourierApplication {
    public static void main(String[] args) {
        SpringApplication.run(CourierApplication.class);
    }
}
