// Folie 102 – Scopes: jede Abfrage liefert eine neue Instanz
package com.example.annotationconfig.scopes;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.time.LocalDateTime;

public class PrototypeDemoApp {

    public static void main(String[] args) throws Exception {
        var ctx = new AnnotationConfigApplicationContext(
                TimeConfig.class);

        var t1 = ctx.getBean(LocalDateTime.class);
        Thread.sleep(100);
        var t2 = ctx.getBean(LocalDateTime.class);

        System.out.println(t1 + " / " + t2);
        ctx.close();
    }
}
