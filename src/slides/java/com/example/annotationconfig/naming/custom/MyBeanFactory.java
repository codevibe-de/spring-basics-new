// Folie 94 – Beans benennen (eigener Name)
// Folie: "public BeanFactory{" -- hier korrigiert zu "public class MyBeanFactory {" (eigener Name, damit es nicht mit Springs BeanFactory kollidiert)
package com.example.annotationconfig.naming.custom;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBeanFactory {

    @Bean("myBean42")
    public MyBean createSomeBean() {
        return new MyBean();
    }
}
