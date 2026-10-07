// Folie 92 – Lifecycle Hooks (per @Bean benannt)
package com.example.annotationconfig.lifecycle;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;

@Configuration
public class SchedulingConfiguration {

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService scheduler() {
        System.out.println("Creating scheduler");
        return Executors.newSingleThreadScheduledExecutor();
    }
}
