package summer;

import org.junit.jupiter.api.Test;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

import static org.assertj.core.api.Assertions.assertThat;

class AnnotationConfigBeanContainerTest {

    @Test
    void scansComponentsAndInjectsDependencies() {
        // given: candidate classes are handed in directly (a simplified "component scan")
        var container = new AnnotationConfigBeanContainer(
                HashMapProductRepository.class,
                ProductService.class,
                CustomerService.class,
                OrderService.class
        );

        // then: every @Component becomes a bean, resolvable by (super-)type
        assertThat(container.getBean(ProductRepository.class)).isInstanceOf(HashMapProductRepository.class);
        assertThat(container.getBean(ProductService.class)).isNotNull();
        assertThat(container.getBean(CustomerService.class)).isNotNull();
        assertThat(container.getBean(OrderService.class)).isNotNull();
    }

    @Test
    void derivesBeanNameFromClassName() {
        // given
        var container = new AnnotationConfigBeanContainer(CustomerService.class);

        // then: default bean name is the decapitalized simple class name
        assertThat(container.getBean("customerService")).isInstanceOf(CustomerService.class);
    }

    @Test
    void ignoresClassesWithoutComponentAnnotation() {
        // given: Address is a plain POJO without @Component
        var container = new AnnotationConfigBeanContainer(pizza.customer.Address.class);

        // then: nothing was registered
        assertThat(container.getBean("address")).isNull();
    }
}
