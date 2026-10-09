// Folie 242 – CommandLineRunner
package com.example.boot.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(0)
public class WelcomingRunner implements CommandLineRunner {

    @Override
    public void run(String... args) {
        System.out.println("Welcome");
    }

}
