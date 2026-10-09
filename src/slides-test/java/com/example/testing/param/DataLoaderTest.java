// Folie 191 – Parametrisiert über Bean-Namen
package com.example.testing.param;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.DataLoader;
import pizza.PizzaApp;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(PizzaApp.class)
@TestPropertySource(properties = "app.data-loader=none")
class DataLoaderTest {

    @Autowired
    Map<String, DataLoader> loaders;
    @Autowired
    ProductService productService;
    @Autowired
    CustomerService customerService;

    @ParameterizedTest(name = "{0}")
    @CsvSource({
            "none,   0, 0",
            "sample, 6, 2",
            "csv,    6, 0"
    })
    @DirtiesContext
    void loaderCreatesData(String beanName, int products, int customers) {
        loaders.get(beanName).run();
        assertThat(productService.getAllProducts()).hasSize(products);
        assertThat(customerService.getAllCustomers()).hasSize(customers);
    }
}
