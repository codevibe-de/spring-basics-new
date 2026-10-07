// Folie 95 – Beans qualifizieren
// Folie: "public BeanFactory{" -- hier korrigiert zu "public class MyBeanFactory {" (eigener Name, damit es nicht mit Springs BeanFactory kollidiert)
package com.example.annotationconfig.qualifier;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanFactory {

    @Bean("myBean31")
    public MyBean createSomeBean() {
        return new MyBean();
    }

    @Bean("myBean42")
    public MyBean createSomeBeanToo() {
        return new MyBean();
    }
}
