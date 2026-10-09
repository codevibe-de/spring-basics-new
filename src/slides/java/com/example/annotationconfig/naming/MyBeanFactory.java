// Folie 95 – Beans benennen
// Folie: "public BeanFactory{" -- hier korrigiert zu "public class MyBeanFactory {" (eigener Name, damit es nicht mit Springs BeanFactory kollidiert)
package com.example.annotationconfig.naming;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanFactory {

    @Bean // default name "createSomeBean"
    public MyBean createSomeBean() {
        return new MyBean();
    }
}
