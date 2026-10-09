// Folie 79 – Konfigurationen zusammensetzen: @Import
package com.example.annotationconfig.importing;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

@Configuration
public class ProductConfig {

    @Bean
    ProductRepository productRepository() {
        return new HashMapProductRepository();
    }

    @Bean
    ProductService productService(ProductRepository repo) {
        return new ProductService(repo);
    }
}
