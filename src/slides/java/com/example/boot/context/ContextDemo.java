// Folie 238 – Mit Kontext arbeiten
package com.example.boot.context;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pizza.PizzaApp;
import pizza.customer.CustomerService;

public class ContextDemo {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(PizzaApp.class, args);

        CustomerService customerService = context.getBean(CustomerService.class);
        var customers = customerService.getAllCustomers();
    }
}
