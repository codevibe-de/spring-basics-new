// Folie 127 – Beispiel Vererbungs-Proxy
package com.example.aop;

import pizza.product.Product;
import pizza.product.ProductRepository;
import pizza.product.ProductService;

class ProductServiceProxy extends ProductService {

    public ProductServiceProxy(ProductRepository productRepository) {
        super(productRepository);
    }

    @Override
    public Product getProduct(String productId) {
        System.out.println("Before invoking the method");
        Product result = super.getProduct(productId);
        System.out.println("After invoking the method");
        return result;
    }
}
