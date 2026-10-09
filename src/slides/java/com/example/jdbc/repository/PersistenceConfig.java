// Folie 206 – @Repository und Exception-Translation
package com.example.jdbc.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.dao.annotation.PersistenceExceptionTranslationPostProcessor;

@Configuration
public class PersistenceConfig {

    @Bean
    static PersistenceExceptionTranslationPostProcessor
            exceptionTranslation() {
        return new
            PersistenceExceptionTranslationPostProcessor();
    }
}
