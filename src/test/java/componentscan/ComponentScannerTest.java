package componentscan;

import org.junit.jupiter.api.Test;
import pizza.customer.Address;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

import static org.assertj.core.api.Assertions.assertThat;

class ComponentScannerTest {

    private final ComponentScanner scanner = new ComponentScanner();

    @Test
    void reportsOnlyAnnotatedClasses() {
        // when: a mix of annotated and non-annotated classes is scanned
        var names = scanner.scan(
                HashMapProductRepository.class,
                ProductService.class,
                CustomerService.class,
                OrderService.class,
                Address.class   // not a @Component
        );

        // then: only the annotated classes are reported
        assertThat(names).containsExactly(
                "productRepository",    // explicit name from @Component(name = ...)
                "productService",       // derived from class name
                "customerService",
                "orderService"
        );
    }

    @Test
    void returnsEmptyListWhenNothingAnnotated() {
        assertThat(scanner.scan(Address.class, String.class)).isEmpty();
    }
}
