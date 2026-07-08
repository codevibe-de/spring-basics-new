package pizza.product;

import java.math.BigDecimal;

public class Product {

    //
    // --- fields ---
    //

    private final String productId;

    private final String name;

    private final BigDecimal price;

    //
    // --- constructors ---
    //

    public Product(String productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    //
    // --- get / set ---
    //

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    //
    // --- other methods ---
    //

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
