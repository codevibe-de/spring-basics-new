package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.DataLoader;
import pizza.PizzaApp;
import pizza.customer.CustomerService;

@SpringJUnitConfig(classes = {PizzaApp.class})
public class MultiInjectTest {

    @Autowired
    private CustomerService customerService;

    private final DataLoader dataLoader;

    @Autowired
    public MultiInjectTest(@Qualifier("sample") DataLoader dataLoader) {
        this.dataLoader = dataLoader;
    }

    @Test
    void testSomething(@Autowired Environment environment) {
        // ...
    }
}
