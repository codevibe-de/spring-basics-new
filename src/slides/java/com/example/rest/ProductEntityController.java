// Folie 266 – Antwort gestalten: ResponseEntity
// Folie „Neuanlage: 201 mit Location“, hinter 266 (Deck mit 290 Folien)
package com.example.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import pizza.product.Product;
import pizza.product.ProductNotFoundException;
import pizza.product.ProductService;

import java.net.URI;

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

    // --- Folie „Neuanlage: 201 mit Location“ ---

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(
            @RequestBody Product product) {
        var created = productService.createProduct(product);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(created.getId())
                .toUri();
        return ResponseEntity.created(location)
                .body(created);
    }
}
