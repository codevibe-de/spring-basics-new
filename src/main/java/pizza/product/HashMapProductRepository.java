package pizza.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
        return productsMap.values().stream().toList();
    }

    @Override
    public Optional<Product> findById(String id) {
        return Optional.ofNullable(productsMap.get(id));
    }
}
