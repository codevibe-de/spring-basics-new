package com.example.testing;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(PizzaApp.class)
@TestPropertySource(
        locations = {"classpath:test.properties"},
        properties = {"app.name=NewName!"}
)
public class AdditionalPropsTest {

    @Autowired
    Environment environment;

    @Test
    void additionalProperties() {
        assertThat(environment.getProperty("app.order.delivery-time-in-minutes")).isEqualTo("12");
        assertThat(environment.getProperty("app.order.discount-rate")).isEqualTo("0.0");
        assertThat(environment.getProperty("app.name")).isEqualTo("NewName!");
    }
}
