// Folie 123 – Beispiel JDK-Proxy
package com.example.aop;

import pizza.product.HashMapProductRepository;
import pizza.product.ProductRepository;

import java.lang.reflect.Proxy;

public class JdkProxyDemo {

    public static void main(String[] args) {
        ProductRepository productRepositoryProxy = (ProductRepository) Proxy.newProxyInstance(
                ProductRepository.class.getClassLoader(),
                new Class[]{ProductRepository.class},
                new AspectInvocationHandler(new HashMapProductRepository()));

        productRepositoryProxy.findAll();
    }
}
