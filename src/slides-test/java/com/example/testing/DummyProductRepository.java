package com.example.testing;

import pizza.product.Product;
import pizza.product.ProductRepository;

import java.util.List;
import java.util.Optional;

public class DummyProductRepository implements ProductRepository {
    @Override
    public Product save(Product product) {
        return null;
    }

    @Override
    public boolean existsById(String productId) {
        return false;
    }

    @Override
    public List<Product> findAll() {
        throw new IllegalStateException("Dummy here!!");
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.empty();
    }
}
