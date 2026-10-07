// Folie 74 – Springs Config-Arten im Überblick (2b. Java Config)
package com.example.annotationconfig.configstyles;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfiguration {
    @Bean
    public CustomerService customerService() {
        return new CustomerService();
    }
}
