package pizza.order;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;
import pizza.PizzaApp;
import pizza.customer.Address;
import pizza.customer.Customer;
import pizza.customer.CustomerService;
import pizza.product.Product;
import pizza.product.ProductRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Test fuer den {@link OrderService}.
 * <p>
 * Ziele dieses Tests:
 * <ol>
 *     <li>
 *         Wir "biegen" das {@link ProductRepository}, das der {@code ProductService} verwendet, auf
 *         eine selbstgeschriebene Test-Dummy-Bean um ({@link DummyProductRepository}). Damit erhalten
 *         wir ein Verhalten, das dem Mocken aehnelt (echtes Mocking hatten wir noch nicht): der
 *         {@code ProductService} liefert deterministische Preise, ohne dass wir vorher echte Produkte
 *         anlegen muessen.
 *     </li>
 *     <li>
 *         Wir demonstrieren {@link DirtiesContext}. Der {@code OrderService} ist ein Singleton und
 *         merkt sich alle Bestellungen in einer internen Liste. Ohne {@code @DirtiesContext} wuerde
 *         die zweite Testmethode die Bestellung aus der ersten Testmethode noch "sehen" und die
 *         Assertions (Groesse der Liste, vergebene Id) wuerden fehlschlagen. {@code @DirtiesContext}
 *         zwingt Spring, den Context nach jeder Testmethode neu aufzubauen -> jede Methode startet mit
 *         einem frischen, "sauberen" {@code OrderService}.
 *     </li>
 * </ol>
 */
@SpringJUnitConfig({PizzaApp.class, OrderServiceTest.TestConfig.class})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class OrderServiceTest {

    @Autowired
    OrderService orderService;

    @Autowired
    CustomerService customerService;

    @Test
    void placeOrder() {
        // given -- ein Kunde muss existieren, sonst wirft placeOrder eine Exception
        Customer customer = registerCustomer("0170-111");

        // when -- die Produkt-Preise kommen aus dem DummyProductRepository:
        //         2 * 8.00 (Margherita) + 1 * 9.50 (Salami) = 25.50, kein Rabatt (siehe TestConfig)
        Order order = orderService.placeOrder(
                customer.getPhoneNumber(),
                Map.of(
                        "PIZZA-MARGHERITA", 2,
                        "PIZZA-SALAMI", 1
                )
        );

        // then
        Assertions.assertThat(order).isNotNull();
        Assertions.assertThat(order.getId()).isEqualTo(1L);
        Assertions.assertThat(order.getCustomer()).isEqualTo(customer);
        Assertions.assertThat(order.getTotalPrice()).isEqualByComparingTo(new BigDecimal("25.50"));

        // und: der OrderService haelt (bis jetzt) genau diese eine Bestellung
        Assertions.assertThat(orderService.getOrders()).hasSize(1);
    }

    @Test
    void placeAnotherOrder() {
        // given
        Customer customer = registerCustomer("0170-222");

        // when -- nur ein Produkt: 3 * 8.00 = 24.00
        Order order = orderService.placeOrder(
                customer.getPhoneNumber(),
                Map.of("PIZZA-MARGHERITA", 3)
        );

        // then -- WICHTIG fuer die Demo: id == 1 und Groesse == 1.
        //         Das funktioniert nur, weil @DirtiesContext den OrderService zwischen den
        //         Testmethoden zuruecksetzt. Ohne @DirtiesContext waere hier id == 2 und size == 2,
        //         je nachdem in welcher Reihenfolge die Tests laufen.
        Assertions.assertThat(order.getId()).isEqualTo(1L);
        Assertions.assertThat(order.getTotalPrice()).isEqualByComparingTo(new BigDecimal("24.00"));
        Assertions.assertThat(orderService.getOrders()).hasSize(1);
    }

    private Customer registerCustomer(String phoneNumber) {
        Address address = new Address("123 Main St", "12345", "Anytown");
        Customer customer = new Customer("John Doe", address, phoneNumber);
        return customerService.createCustomer(customer);
    }

    /**
     * Test-Konfiguration, die zwei Beans als {@link Primary} bereitstellt und damit die "echten"
     * Beans aus dem {@code PizzaApp}-ComponentScan ueberstimmt:
     * <ul>
     *     <li>{@link DummyProductRepository} statt {@code HashMapProductRepository}</li>
     *     <li>eine {@link OrderProperties}-Bean ohne Rabatt, damit die Preis-Assertions deterministisch
     *         sind (unabhaengig vom aktuellen Wochentag)</li>
     * </ul>
     * Bewusst <b>nicht</b> mit {@code @Configuration} annotiert: {@code PizzaApp} macht einen
     * {@code @ComponentScan} ueber das gesamte {@code pizza}-Package (inkl. der Test-Klassen). Waere
     * diese Klasse eine stereotypisierte {@code @Configuration}, wuerde der Scan sie aufsammeln und
     * die {@code @Primary}-Beans wuerden in <i>alle</i> Tests "durchsickern" (z.B. in den
     * {@code ProductServiceTest}). Da wir die Klasse oben in {@code @SpringJUnitConfig} explizit
     * auflisten, werden ihre {@code @Bean}-Methoden trotzdem verarbeitet -- aber eben nur hier.
     */
    static class TestConfig {

        @Bean
        @Primary
        ProductRepository dummyProductRepository() {
            return new DummyProductRepository();
        }

        @Bean
        @Primary
        OrderProperties testOrderProperties() {
            // keine Rabatt-Tage -> Gesamtpreis == Summe der Produktpreise, gut testbar
            return new OrderProperties(12, List.of(), 0.0);
        }
    }

    /**
     * Ein von Hand geschriebenes Dummy-{@link ProductRepository} mit fest verdrahteten Produkten.
     * Ersetzt das echte Repository, damit der {@code ProductService} im Test bekannte Preise liefert
     * (mocking-aehnliches Verhalten ohne Mocking-Framework).
     */
    static class DummyProductRepository implements ProductRepository {

        private final Map<String, Product> products = Map.of(
                "PIZZA-MARGHERITA", new Product("PIZZA-MARGHERITA", "Pizza Margherita", new BigDecimal("8.00")),
                "PIZZA-SALAMI", new Product("PIZZA-SALAMI", "Pizza Salami", new BigDecimal("9.50"))
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
