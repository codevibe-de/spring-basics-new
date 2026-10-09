// Folie 93 – Lifecycle Hooks
package com.example.annotationconfig.lifecycle;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.time.LocalTime;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@Component
public class HeartbeatLogger {

    private final ScheduledExecutorService scheduler;
    private ScheduledFuture<?> heartbeat;

    public HeartbeatLogger(ScheduledExecutorService scheduler) {
        this.scheduler = scheduler;
    }

    @PostConstruct
    public void start() {
        System.out.println("Starting heartbeat");
        heartbeat = scheduler.scheduleAtFixedRate(
                () -> System.out.println("still alive: " + LocalTime.now()),
                0, 2, TimeUnit.SECONDS);
    }

    @PreDestroy
    public void stop() {
        System.out.println("Stopping heartbeat");
        heartbeat.cancel(false);
    }
}
