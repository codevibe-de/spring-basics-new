// Folie 83 – Constructor Injection
package com.example.annotationconfig.injection.constructor;

import org.springframework.stereotype.Service;
import pizza.product.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    // ...
}
