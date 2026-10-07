// Folie „Auf den fertigen Kontext reagieren“ (025, Fortgeschrittene Konzepte) – @EventListener
package com.example.annotationconfig.events;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class ContextReadyLogger {

    @EventListener
    public void onReady(ContextRefreshedEvent e) {
        var ctx = e.getApplicationContext();
        var n = ctx.getBeanDefinitionCount();
        System.out.println(n + " Beans bereit");
    }
}
