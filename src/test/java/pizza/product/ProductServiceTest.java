package pizza.product;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(PizzaApp.class)
class ProductServiceTest {

    @Autowired
    ProductService productService;

    @Test
    void getTotalPrice() {
        // given
        Product product1 = new Product("T-1", "Test Product 1", new BigDecimal("10.00"));
        productService.createProduct(product1);
        Product product2 = new Product("T-2", "Test Product 2", new BigDecimal("0.90"));
        productService.createProduct(product2);

        // when
        BigDecimal totalPrice = productService.getTotalPrice(
                Map.of(
                        product1.getProductId(), 2,
                        product2.getProductId(), 3
                )
        );

        // then
        var expectedTotalPrice = new BigDecimal("22.70");   // 2 * 10 + 3 * 0.90 = 20 + 2.70 = 22.70
        Assertions.assertThat(totalPrice).isEqualByComparingTo(expectedTotalPrice);
    }

}