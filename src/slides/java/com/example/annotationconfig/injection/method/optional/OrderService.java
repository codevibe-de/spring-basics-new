// Folie 86 – Method Injection – optionale Beans
// @Nullable aus JSpecify (org.jspecify.annotations), nicht das in Spring 7 deprecated org.springframework.lang.Nullable
package com.example.annotationconfig.injection.method.optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

@Service
public class OrderService {

    private CustomerService customerService;
    private ProductService productService;

    public OrderService() {
    }

    @Autowired(required = false)
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(@Nullable ProductService productService) {
        this.productService = productService;
    }
}
