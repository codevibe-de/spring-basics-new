// Folie 78 – Hilfsklasse: pizza.order.OrderService braucht ab 030 zusätzlich OrderProperties
package com.example.annotationconfig.beanmethods;

import pizza.customer.CustomerService;
import pizza.product.ProductService;

public class OrderService {

    private final CustomerService customerService;
    private final ProductService productService;

    public OrderService(CustomerService customerService,
                        ProductService productService) {
        this.customerService = customerService;
        this.productService = productService;
    }
}
