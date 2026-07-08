package pizza.product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository {

    Product save(Product product);

    boolean existsById(String productId);

    List<Product> findAll();

    Optional<Product> findById(String productId);
}
