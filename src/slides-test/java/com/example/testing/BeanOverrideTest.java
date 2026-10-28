// Folie 182 – Beans überschreiben
package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.product.ProductRepository;

@SpringJUnitConfig(classes = {PizzaApp.class, BeanOverrideTest.SomeTestConfig.class})
public class BeanOverrideTest {

    @Autowired
    ProductRepository productRepository;

    @Test
    void init() {}

    @Configuration
    static class SomeTestConfig {

        // REPLACES the scanned bean "hashMapProductRepository"
        @Bean("hashMapProductRepository")
        public ProductRepository dummyProductRepository1() {
            return new DummyProductRepository();
        }

        // ----- OR ------

        // creates an ADDITIONAL bean, which wins
        // in regular injection since it's primary
        @Bean
        @Primary
        public ProductRepository dummyProductRepository2() {
            return new DummyProductRepository();
        }
    }
}
