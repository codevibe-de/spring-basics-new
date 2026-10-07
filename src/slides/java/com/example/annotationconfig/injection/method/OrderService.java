// Folie 85 – Method Injection
package com.example.annotationconfig.injection.method;

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

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
}
