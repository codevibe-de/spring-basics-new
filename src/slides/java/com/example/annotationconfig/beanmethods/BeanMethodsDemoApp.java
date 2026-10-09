// Folie 78 – @Bean-Methoden rufen sich gegenseitig auf
package com.example.annotationconfig.beanmethods;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.product.ProductService;

public class BeanMethodsDemoApp {

    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(
                PizzaConfig.class, LiteConfig.class)) {
            var full = ctx.getBean(PizzaConfig.class);
            var lite = ctx.getBean(LiteConfig.class);

            // PizzaConfig$$SpringCGLIB$$0 vs. LiteConfig
            System.out.println(full.getClass().getSimpleName());
            System.out.println(lite.getClass().getSimpleName());

            // Proxy liefert die Bean aus dem Kontext: true
            System.out.println(full.productService()
                    == full.productService());
            // ohne Proxy jedes Mal ein neues Objekt: false
            System.out.println(lite.productService()
                    == lite.productService());
        }
    }
}
