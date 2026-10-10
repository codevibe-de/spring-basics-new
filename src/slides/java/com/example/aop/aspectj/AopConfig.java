// Folie 117 – Aspekte mit Annotationen (Konfiguration)
package com.example.aop.aspectj;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pizza.customer.CustomerService;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan
public class AopConfig {

    @Bean
    CustomerService customerService() {
        return new CustomerService();
    }
}
