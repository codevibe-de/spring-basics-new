// Folie 149 – Environment
package com.example.properties;

import org.springframework.core.env.Environment;

public class EnvironmentAccess {

    static void readProperties(Environment environment) {
        String title = environment.getProperty("app.title", "My Super App");
        String version = environment.getProperty("app.version");
        boolean active = environment.getProperty("app.enabled", Boolean.class, true);
    }
}
