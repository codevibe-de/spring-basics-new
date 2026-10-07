// Folie 76 – Bean Definitionen per @Service oder @Component
package com.example.annotationconfig.stereotypes;

import org.springframework.stereotype.Service;
import pizza.product.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
}
