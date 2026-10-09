// Folie 80 – Component-Scan (neu ohne Spring Boot)
// Auf der Folie steht "package pizza;" -- hier unter com.example, damit nichts mit der echten App kollidiert.
package com.example.annotationconfig.componentscan;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan({"pizza", "com.other"})
public class PizzaApp {
    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(PizzaApp.class)) {
            // ...
        }
    }
}
