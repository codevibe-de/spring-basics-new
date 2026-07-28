package com.example.spel;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class SpelBean {

    @Value("#{@namingService.prefix + 'me'.toUpperCase()}")
    String someName;

    @PostConstruct
    void print() {
        System.out.println(someName);
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(SpelBean.class, NamingService.class);
    }
}
