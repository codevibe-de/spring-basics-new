// Folie „Annotationen und XML mischen“ (hinter „Component-Scan“)
// Folie 75 – Bean Definitionen per XML (Mischform mit @ImportResource)
// Recap Folie 273 – XML-Konfiguration in einer Spring-Anwendung
package com.example.annotationconfig.xmlconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import pizza.customer.CustomerService;
import pizza.product.ProductService;

@Configuration
@ComponentScan("pizza.product")
@ImportResource("classpath:beans.xml")
public class MixedConfigApp {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(MixedConfigApp.class)) {
            // ProductService kommt aus dem Scan, CustomerService aus beans.xml
            ProductService productService = context.getBean(ProductService.class);
            CustomerService customerService = context.getBean(CustomerService.class);
            System.out.println(productService.getAllProducts());
            System.out.println(customerService.getAllCustomers());
        }
    }
}
