// Folie 169 – Verbindung JUnit mit Spring - alternativ
package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.customer.CustomerService;

@SpringJUnitConfig(classes = PizzaApp.class)
public class AnotherTest {

    @Autowired
    CustomerService customerService;

    @Test
    void init() {}

}
