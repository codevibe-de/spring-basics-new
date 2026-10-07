// Folie 76 – Bean Definitionen per @Service oder @Component
package com.example.annotationconfig.stereotypes;

import org.springframework.stereotype.Component;
import pizza.product.Product;
import pizza.product.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class HashMapProductRepository implements ProductRepository {

    private final Map<String, Product> productsMap = new HashMap<>();

    @Override
    public Product save(Product product) {
        productsMap.put(product.getId(), product);
        return product;
    }

    @Override
    public boolean existsById(String id) {
        return productsMap.containsKey(id);
    }

    @Override
    public List<Product> findAll() {
        return List.copyOf(productsMap.values());
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productsMap.get(id));
    }
}
