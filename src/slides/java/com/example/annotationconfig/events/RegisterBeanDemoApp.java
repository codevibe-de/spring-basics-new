// Folie „Beans programmatisch registrieren“ (025, Fortgeschrittene Konzepte) – registerBean() vor refresh()
package com.example.annotationconfig.events;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.PizzaApp;

import java.time.Clock;

public class RegisterBeanDemoApp {

    public static void main(String[] args) {
        var ctx = new AnnotationConfigApplicationContext();
        ctx.register(PizzaApp.class);
        ctx.registerBean(Clock.class, Clock::systemDefaultZone);
        ctx.refresh();

        System.out.println(ctx.getBean(Clock.class));
        ctx.close();
    }
}
