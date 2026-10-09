// Folie 98 – Primäre Beans
package com.example.annotationconfig.primary;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class MyBeanFactory {

    @Bean
    @Primary
    public MyBean createSomeBean() {
        return new MyBean();
    }

    @Bean
    public MyBean otherBean() {
        return new MyBean();
    }
}
