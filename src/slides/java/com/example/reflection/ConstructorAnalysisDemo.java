// Folie 34 – Grundlagen Java Reflection – Analyse der Konstruktoren
package com.example.reflection;

import pizza.order.OrderService;

import java.lang.reflect.Constructor;

public class ConstructorAnalysisDemo {

    public static void main(String[] args) {
        Constructor<?>[] constructors = OrderService.class.getDeclaredConstructors();
        for (Constructor<?> constructor : constructors) {
            Class<?>[] parameterTypes = constructor.getParameterTypes();
            System.out.println("Constructor with " + parameterTypes.length + " parameters:");
        }
    }
}
