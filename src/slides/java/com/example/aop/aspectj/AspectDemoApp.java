// Folie 117 – Aspekte mit Annotationen (Demo)
package com.example.aop.aspectj;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.customer.CustomerService;

public class AspectDemoApp {

    public static void main(String[] args) {
        try (var ctx = new AnnotationConfigApplicationContext(AopConfig.class)) {
            var customerService = ctx.getBean(CustomerService.class);

            // CustomerService$$SpringCGLIB$$0: der Proxy um die Bean
            System.out.println(customerService.getClass().getSimpleName());

            // läuft durch den LoggingAspect
            customerService.getAllCustomers();
        }
    }
}
