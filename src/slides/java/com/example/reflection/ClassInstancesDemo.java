// Folie 33 – Grundlagen Java Reflection – Class<?>
package com.example.reflection;

import pizza.customer.CustomerService;

public class ClassInstancesDemo {

    public static void main(String[] args) throws ClassNotFoundException {
        CustomerService customerService = new CustomerService();

        // 1. statischer Zugriff über die Klasse
        Class<?> serviceClass1 = CustomerService.class;

        // 2. statischer Zugriff via vollqualifiziertem Namen
        Class<?> serviceClass2 = Class.forName("pizza.customer.CustomerService");

        // 3. get-Methode auf Instanz
        Class<?> serviceClass3 = customerService.getClass();

        System.out.println(serviceClass1 == serviceClass2 && serviceClass2 == serviceClass3);
    }
}
