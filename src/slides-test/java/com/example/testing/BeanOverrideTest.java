// Folie 179 – Beans überschreiben
// Korrigiert gegenüber Folie v3.1: keine Boot-Annotation @TestConfiguration, sondern eine innere
// @Configuration wie auf Folie 172; kein Boot-Property spring.main.allow-bean-definition-overriding
// (reines Spring erlaubt das Überschreiben per Default); Bean-Name der Kapitel-040-App statt "productJdbcDao".
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

        // this will REPLACE an existing bean with name "hashMapProductRepository"
        // (Spring Boot only allows this with spring.main.allow-bean-definition-overriding=true)
        @Bean("hashMapProductRepository")
        public ProductRepository noOpProductRepository1() {
            return new NoOpProductRepository();
        }

        // ----- ODER ------

        // this will create an ADDITIONAL bean, which will be used in
        // regular injection since it's primary
        @Bean("noOpProductRepository")
        @Primary
        public ProductRepository noOpProductRepository2() {
            return new NoOpProductRepository();
        }
    }
}
