// Folie 35 – Grundlagen Java Reflection – Dynamische Instanziierung
package com.example.reflection;

import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

public class DynamicInstantiationDemo {

    public static void main(String[] args) throws ReflectiveOperationException {
        var constructorArgs = new Object[]{
                new HashMapProductRepository()
        };

        Object untypedInstance = ProductService.class
                .getConstructors()[0]
                .newInstance(constructorArgs);
        ProductService productService = (ProductService) untypedInstance;

        System.out.println(productService.getAllProducts());
    }
}
