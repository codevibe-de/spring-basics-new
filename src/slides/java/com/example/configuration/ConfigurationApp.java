package com.example.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

@Configuration
@ComponentScan
@PropertySource("com/example/configuration/placeholder.properties")
public class ConfigurationApp {

    @Value("${app.name}")
    String s;
    @Value("${app.description}")
    String d;

    @Autowired
    Environment environment;

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ConfigurationApp.class);
    }

    @PostConstruct
    void init() {
        System.out.println(s);
        System.out.println(d);
        System.out.println(environment.getProperty("app.name"));
        System.out.println(environment.getProperty("app.version"));
    }

}
