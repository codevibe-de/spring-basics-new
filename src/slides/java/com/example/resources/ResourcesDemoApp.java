// Keine Folie mit Code – Live Coding / Übung b) Kapitel 027 (Resources per ApplicationContext)
package com.example.resources;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

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
