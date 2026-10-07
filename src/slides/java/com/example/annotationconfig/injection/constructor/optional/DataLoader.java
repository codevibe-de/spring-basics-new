// Folie 83 – Constructor Injection - Besonderheiten (Optional)
package com.example.annotationconfig.injection.constructor.optional;

import pizza.customer.CustomerService;
import pizza.product.ProductService;

import java.util.Optional;

class DataLoader {
    public DataLoader(
            ProductService productService,
            Optional<CustomerService> optionalCustomerService
    ) { }
}
