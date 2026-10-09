// Folie 85 – Constructor Injection - Besonderheiten (@Nullable)
// @Nullable aus JSpecify (org.jspecify.annotations), nicht das in Spring 7 deprecated org.springframework.lang.Nullable
package com.example.annotationconfig.injection.constructor.nullable;

import org.jspecify.annotations.Nullable;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

class DataLoader {
    public DataLoader(
            ProductService productService,
            @Nullable CustomerService customerService
    ) { }
}
