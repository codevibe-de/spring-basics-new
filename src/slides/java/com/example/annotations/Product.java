// Folie 59 – Wichtige Standard-Annotationen (in Java selbst)
package com.example.annotations;

import java.math.BigDecimal;

public class Product {

    private final String productId;
    private final String name;
    private final BigDecimal price;

    public Product(String productId, String name, BigDecimal price) {
        this.productId = productId;
        this.name = name;
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId='" + productId + '\'' +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }
}
