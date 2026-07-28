package com.example.testing;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pizza.PizzaApp;
import pizza.customer.CustomerService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = PizzaApp.class)
public class SomeTest {

    @Autowired
    CustomerService customerService;

    @Test
    void init() {}

}
