// Folien 260, 262 – Hilfsklasse: pizza.product.ProductService kennt kein deleteProductsWithNamePrefix()
package com.example.rest;

import org.springframework.stereotype.Service;
import pizza.product.Product;
import pizza.product.ProductNotFoundException;

import java.util.HashMap;
import java.util.Map;

@Service
public class ProductService {

    private final Map<String, Product> productsById = new HashMap<>();

    public Product getProduct(String id) {
        var product = productsById.get(id);
        if (product == null) {
            throw new ProductNotFoundException("For id " + id);
        }
        return product;
    }

    public int deleteProductsWithNamePrefix(String namePrefix) {
        int sizeBefore = productsById.size();
        productsById.values().removeIf(p -> p.getName().startsWith(namePrefix));
        return sizeBefore - productsById.size();
    }
}
