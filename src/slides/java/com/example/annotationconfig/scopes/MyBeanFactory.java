// Folie 104 – Scopes
package com.example.annotationconfig.scopes;

import com.example.annotationconfig.naming.MyBean;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class MyBeanFactory {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public MyBean myBeanPrototype() {
        return new MyBean();
    }
}
