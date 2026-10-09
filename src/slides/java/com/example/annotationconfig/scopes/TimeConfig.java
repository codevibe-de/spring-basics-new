// Folie 104 – Scopes: PROTOTYPE mit LocalDateTime
package com.example.annotationconfig.scopes;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import java.time.LocalDateTime;

@Configuration
public class TimeConfig {

    @Bean
    @Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
    public LocalDateTime now() {
        return LocalDateTime.now();
    }
}
