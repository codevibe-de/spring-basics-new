// Folie 74 – Springs Config-Arten im Überblick (1. Xml Config)
// Folie 75 – Bean Definitionen per XML (context.xml unter src/slides/resources)
package com.example.annotationconfig.xmlconfig;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import pizza.customer.CustomerService;

public class XmlConfigApp {

    public static void main(String[] args) {
        try (var context = new ClassPathXmlApplicationContext("context.xml")) {
            CustomerService customerService = context.getBean(CustomerService.class);
            System.out.println(customerService.getAllCustomers());
        }
    }
}
