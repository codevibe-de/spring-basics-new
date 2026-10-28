// Folie 80 – Component-Scan (neu ohne Spring Boot)
package com.example.annotationconfig.componentscan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pizza.util.CommandLineRunner;

@Configuration
@ComponentScan({"pizza", "com.other"})
public class PizzaApp {

    public static void main(String[] args) {
        // Instantiate annotation configured context ---
        try (var beanContainer = new AnnotationConfigApplicationContext(PizzaApp.class)) {
            // get runners and run them
            beanContainer.getBeanProvider(CommandLineRunner.class)
                    .orderedStream()
                    .forEach(runner -> runner.run(args));
        }
    }

}
