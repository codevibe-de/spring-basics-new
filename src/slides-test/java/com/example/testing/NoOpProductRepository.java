// Folie 182 – Beans überschreiben (Hilfsklasse)
package com.example.testing;

import pizza.product.Product;
import pizza.product.ProductRepository;

import java.util.List;
import java.util.Optional;

public class NoOpProductRepository implements ProductRepository {

    @Override
    public Product save(Product product) {
        return product;
    }

    @Override
    public boolean existsById(String id) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        return List.of();
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.empty();
    }
}
