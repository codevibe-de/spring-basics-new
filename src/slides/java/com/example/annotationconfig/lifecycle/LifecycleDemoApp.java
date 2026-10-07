// Folie 91/92 – Lifecycle Hooks: Demo. Beim Schließen des Kontexts läuft zuerst
// HeartbeatLogger.stop() (@PreDestroy), danach shutdown() am Executor (destroyMethod).
// Ohne shutdown() liefe der Executor-Thread weiter und die JVM würde nicht beenden.
package com.example.annotationconfig.lifecycle;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class LifecycleDemoApp {

    public static void main(String[] args) throws InterruptedException {
        try (var context = new AnnotationConfigApplicationContext(
                SchedulingConfiguration.class, HeartbeatLogger.class)) {
            Thread.sleep(5_000);
        }
        System.out.println("Context closed");
    }
}
