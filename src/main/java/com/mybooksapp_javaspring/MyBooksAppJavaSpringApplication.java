package com.mybooksapp_javaspring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class MyBooksAppJavaSpringApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBooksAppJavaSpringApplication.class, args);
    }

}
