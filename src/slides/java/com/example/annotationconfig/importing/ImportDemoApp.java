// Folie 79 – Konfigurationen zusammensetzen: @Import
package com.example.annotationconfig.importing;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

public class ImportDemoApp {

    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(
                AppConfig.class)) {
            System.out.println(ctx.getBean(ProductService.class));
            System.out.println(ctx.getBean(CustomerService.class));
        }
    }
}
