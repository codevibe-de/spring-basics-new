// Folie 100 – Listen von Beans
package com.example.annotationconfig.collections;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class MyBeanConfig {

    @Bean
    @Order(1)
    MyBean myBean1() { return new MyBean(); }

    @Bean
    @Order(22)
    MyBean myBean2() { return new MyBean(); }
}
