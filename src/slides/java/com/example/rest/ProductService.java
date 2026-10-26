// Folien 263, 265 und „RestController – PUT“ – Hilfsklasse: pizza.product.ProductService kennt
// weder deleteProductsWithNamePrefix() noch updateProduct()
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

    public Product updateProduct(String id, Product product) {
        getProduct(id);
        var updated = new Product(id, product.getName(), product.getPrice());
        productsById.put(id, updated);
        return updated;
    }

    public int deleteProductsWithNamePrefix(String namePrefix) {
        int sizeBefore = productsById.size();
        productsById.values().removeIf(p -> p.getName().startsWith(namePrefix));
        return sizeBefore - productsById.size();
    }
}
