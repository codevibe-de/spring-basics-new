package com.example.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
public class EnvironmentReader {

    private final Environment environment;

    public EnvironmentReader(Environment environment) {
        this.environment = environment;
    }

    @PostConstruct
    void init() {
        String title = environment.getProperty("app.title", "My Super App");
        String version = environment.getProperty("app.version");
        boolean active = environment.getProperty("app.enabled", Boolean.class, true);
    }
}
