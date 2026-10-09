// Folie 75 – Bean Definitionen per XML (Mischform mit @ImportResource)
// Recap Folie 273 – XML-Konfiguration in einer Spring-Anwendung
package com.example.annotationconfig.xmlconfig;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import pizza.customer.CustomerService;

@Configuration
@ImportResource("classpath:context.xml")
public class MixedConfigApp {

    public static void main(String[] args) {
        try (var context = new AnnotationConfigApplicationContext(MixedConfigApp.class)) {
            CustomerService customerService = context.getBean(CustomerService.class);
            System.out.println(customerService.getAllCustomers());
        }
    }
}
