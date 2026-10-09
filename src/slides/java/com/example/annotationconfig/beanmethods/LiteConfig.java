// Folie 78 – ohne Proxy: proxyBeanMethods = false
package com.example.annotationconfig.beanmethods;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

@Configuration(proxyBeanMethods = false)
public class LiteConfig {

    @Bean
    ProductService productService() {
        return new ProductService(
                new HashMapProductRepository());
    }
}
