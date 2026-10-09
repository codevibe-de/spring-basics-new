// Folie 176 – Kontext zurücksetzen
package com.example.testing;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.customer.Customer;
import pizza.customer.CustomerService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(PizzaApp.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class DirtiesContextTest {

    @Autowired
    CustomerService customerService;

    @Test
    @Order(1)
    @DirtiesContext
        // this test changes the singleton CustomerService
    void createCustomer() {
        customerService.createCustomer(new Customer("Erika Test", null, "0931-4711"));

        assertThat(customerService.getCustomerByPhoneNumber("0931-4711")).isNotNull();
    }

    @Test
    @Order(2)
    void freshContextAfterwards() {
        // new context, new CustomerService: the customer from test 1 is gone
        assertThat(customerService.getAllCustomers())
                .noneMatch(c -> c.getPhoneNumber().equals("0931-4711"));
    }
}
