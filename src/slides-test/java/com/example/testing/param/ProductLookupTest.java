// Folie 187 – Parametrisierte Tests
package com.example.testing.param;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.product.ProductService;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(PizzaApp.class)
class ProductLookupTest {

    @ParameterizedTest
    @ValueSource(strings = {"S-01", "P-10", "P-12"})
    void findsSampleProduct(
            String id,
            @Autowired ProductService productService
    ) {
        var product = productService.getProduct(id);
        assertThat(product.getId()).isEqualTo(id);
    }
}
