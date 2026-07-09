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

    public Product getProduct(String productId) {
        return productRepository
                .findById(productId)
                .orElseThrow(() -> new ProductNotFoundException("For id " + productId));
    }

    public BigDecimal getTotalPrice(Map<String, Integer> productQuantities) {
        BigDecimal result = BigDecimal.ZERO;
        // TODO implement calculation
        return result;
    }

    public Product createProduct(Product product) {
        if (productRepository.existsById(product.getProductId())) {
            throw new IllegalStateException("The product-repository already contains a product with id " + product.getProductId());
        }
        return productRepository.save(product);
    }
}
