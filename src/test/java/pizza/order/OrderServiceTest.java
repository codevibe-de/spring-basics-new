package pizza.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import org.springframework.util.ReflectionUtils;
import pizza.PizzaApp;
import pizza.product.Product;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@SpringJUnitConfig({PizzaApp.class})
// TODO @TestPropertySource so befüllen, dass der Test läuft.
//          - Es muss der "sample"-DataLoader aktiv sein (sonst fehlen Kunde & Produkte im Test).
//          - Denk außerdem daran, dass der Gesamtpreis vom (tagesabhängigen!) Rabatt abhängt:
//            damit der Test deterministisch ist, sollte die Rabatt-Konfiguration hier fixiert werden.
@TestPropertySource(properties = {
        // TODO diesen Platzhalter durch die passende(n) Property(s) ersetzen
        "todo=todo"
})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@Disabled("zum Bearbeiten diese @Disabled-Annotation entfernen")
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Test
    // TODO Annotation ergänzen, damit der in diesem Test (per Reflection-Hack) veränderte
    //      ApplicationContext danach verworfen und für den nächsten Test neu aufgebaut wird.
    @org.junit.jupiter.api.Order(1)
    void placeOrder(@Autowired ProductService productService) {
        // Overwrite the productRepository instance in the productService with our dummy repository.
        // This is a bit of a hack (which will fail in later Java versions), but it works for testing purposes.
        ReflectionUtils.doWithFields(ProductService.class, field -> {
            if (field.getName().equals("productRepository")) {
                field.setAccessible(true);
                field.set(productService, new DummyProductRepository());
            }
        });

        // when -- die Produkt-Preise kommen aus dem DummyProductRepository:
        //         2 * 8.00 (Margherita) + 1 * 10.00 (Salami) = 26.00, kein Rabatt (via @TestPropertySource abgeschaltet)
        Order order = orderService.placeOrder(
                "+49 123 456789",
                Map.of(
                        "PM", 2,
                        "PS", 1
                )
        );

        // then
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getId()).isEqualTo(1L);
        Assertions.assertThat(order.getTotalPrice()).isEqualByComparingTo(new BigDecimal("26.00"));

        // und: der OrderService hält genau diese eine Bestellung
        Assertions.assertThat(orderService.getOrders()).hasSize(1);
    }


    /**
     * This test works with the original ProductRepository bean -- and fails, if the previous test hasn't cleaned up
     * its modified context.
     */
    @Test
    @org.junit.jupiter.api.Order(2)
    void placeAnotherOrder() {
        // when
        Order order = orderService.placeOrder(
                "+49 123 456789",
                Map.of("S-01", 3)
        );

        // then
        Assertions.assertThat(order.getTotalPrice()).isEqualByComparingTo(new BigDecimal("20.70"));
    }


    /**
     * Ein von Hand geschriebenes Dummy-{@link ProductRepository} mit fest verdrahteten Produkten.
     * Ersetzt das echte Repository, damit der {@code ProductService} im Test bekannte Preise liefert
     * (mocking-ähnliches Verhalten ohne Mocking-Framework).
     */
    static class DummyProductRepository implements ProductRepository {

        private final Map<String, Product> products = Map.of(
                "PM", new Product("PM", "Fake Pizza Margherita", new BigDecimal("8.00")),
                "PS", new Product("PS", "Fake Pizza Salami", new BigDecimal("10.00"))
        );

        @Override
        public Product save(Product product) {
            throw new UnsupportedOperationException("Dummy repository is read-only");
        }

        @Override
        public boolean existsById(String productId) {
            return products.containsKey(productId);
        }

        @Override
        public List<Product> findAll() {
            return List.copyOf(products.values());
        }

        @Override
        public Optional<Product> findById(String productId) {
            return Optional.ofNullable(products.get(productId));
        }
    }
}
