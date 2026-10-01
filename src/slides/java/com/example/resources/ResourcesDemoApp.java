package com.example.resources;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pizza.util.CommandLineRunner;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class ResourcesDemoApp {

    public static void main(String[] args) throws IOException {
        // Instantiate annotation configured context ---
        try (var beanContainer = new AnnotationConfigApplicationContext(ResourcesDemoApp.class)) {
            var resourcePath = "https://docs.spring.io/spring-framework/reference/_/img/spring-logo.svg";
            var resource = beanContainer.getResource(resourcePath);
            System.out.println(resource.isReadable());
            System.out.println(resource.contentLength());
            resource.getContentAsString(StandardCharsets.UTF_8).lines().limit(5).forEach(System.out::println);
        }
    }

}
