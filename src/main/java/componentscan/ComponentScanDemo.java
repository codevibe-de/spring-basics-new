package componentscan;

import pizza.customer.Address;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

/**
 * Runnable demonstration of the {@link ComponentScanner}. It feeds a mix of annotated and
 * non-annotated classes to the scanner – only the {@link Component}-annotated ones are reported.
 */
public class ComponentScanDemo {

    public static void main(String[] args) {
        new ComponentScanner().scan(
                HashMapProductRepository.class,   // @Component(name = "productRepository")
                ProductService.class,             // @Component
                CustomerService.class,            // @Component
                OrderService.class,               // @Component
                Address.class                     // no @Component -> ignored
        );
    }
}
