// Folie 78 – @Bean-Methoden rufen sich gegenseitig auf
package com.example.annotationconfig.beanmethods;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

@Configuration
public class PizzaConfig {

    @Bean
    CustomerService customerService() {
        return new CustomerService();
    }

    @Bean
    ProductService productService() {
        return new ProductService(
                new HashMapProductRepository());
    }

    @Bean
    OrderService orderService() {
        return new OrderService(customerService(),
                productService());
    }
}
