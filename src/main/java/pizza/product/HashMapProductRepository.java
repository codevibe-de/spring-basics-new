package pizza.product;

import componentscan.Component;

import java.util.*;

@Component(name = "productRepository")
public class HashMapProductRepository implements ProductRepository {

    private final Map<String, Product> productsMap = new HashMap<>();

    @Override
    public Product save(Product product) {
        productsMap.put(product.getProductId(), product);
        return product;
    }

    @Override
    public boolean existsById(String productId) {
        return productsMap.containsKey(productId);
    }

    @Override
    public List<Product> findAll() {
        return productsMap.values().stream().toList();
    }

    @Override
    public Optional<Product> findById(String productId) {
        return Optional.ofNullable(productsMap.get(productId));
    }
}
