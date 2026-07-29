package pizza.product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

public class ProductService {

    private final ProductRepository productRepository;

    //
    // constructors and setup
    //

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    //
    // business logic
    //

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProduct(String id) {
        return productRepository
                .findById(id)
                .orElseThrow(() -> new ProductNotFoundException("For id " + id));
    }

    public BigDecimal getTotalPrice(Map<String, Integer> productQuantities) {
        return productQuantities.entrySet().stream()
                .map(entry -> {
                    Product product = getProduct(entry.getKey());
                    BigDecimal quantity = BigDecimal.valueOf(entry.getValue());
                    return quantity.multiply(product.getPrice());
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getId())) {
            throw new IllegalStateException("The product-repository already contains a product with id " + product.getId());
        }
        return productRepository.save(product);
    }
}
