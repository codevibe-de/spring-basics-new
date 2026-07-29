package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.customer.CustomerService;

@SpringJUnitConfig(classes = {PizzaApp.class, InnerConfigTest.TestConfig.class})
public class InnerConfigTest {

    @Autowired
    CustomerService customerService;
    @Autowired
    StringBuilder stringBuilder;

    @Test
    void init() {}

    @Configuration
    static class TestConfig {
        @Bean
        StringBuilder stringBuilder() {
            return new StringBuilder();
        }
    }

}
