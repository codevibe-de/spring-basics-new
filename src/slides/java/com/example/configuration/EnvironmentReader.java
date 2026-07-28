package com.example.configuration;

import jakarta.annotation.PostConstruct;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.awt.geom.Point2D;

@Component
public class EnvironmentReader {

    private final Environment env;

    public EnvironmentReader(Environment env) {
        this.env = env;
    }

    @PostConstruct
    void init() {
        String version = env.getRequiredProperty("app.version");

        String title = env.getProperty("app.title", "My Super App");
        boolean active = env.getProperty("app.enabled", Boolean.class, true);

        Point2D loc = env.getProperty("app.default-location",
                Point2D.class, new Point2D.Double(0, 0));
    }
}
