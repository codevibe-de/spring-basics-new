// Folie 266 – Antwort gestalten: ResponseEntity
package com.example.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pizza.product.Product;
import pizza.product.ProductNotFoundException;
import pizza.product.ProductService;

@RestController
public class ProductEntityController {

    private final ProductService productService;

    public ProductEntityController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> getProduct(
            @PathVariable String id
    ) {
        try {
            var product = productService.getProduct(id);
            return ResponseEntity.ok(product);
        } catch (ProductNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
