// Folie 40 – Bean-Definitionen verwalten
// Folie 46 – Beans ausliefern
package com.example.beancontainer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Folienstand des selbst gebauten Bean-Containers: nur Definieren und Ausliefern.
 * Die Instanziierung (Abhängigkeits-Graph, Reflection) wird im Live Coding entwickelt,
 * siehe Branch 011-beanContainer-solution ({@code summer.BeanContainer}).
 */
public class BeanContainer {

    // --- Folie 40 ---

    List<BeanDefinition> beanDefs = new ArrayList<>();

    public void defineBean(String name, Class<?> type) {
        this.beanDefs.add(
                new BeanDefinition(name, type)
        );
    }

    // --- Folie 46 ---

    private final Map<String, Object> beansByNameMap = new HashMap<>();

    // "gib mir die Bean mit Namen Xyz"
    public Object getBean(String name) {
        return this.beansByNameMap.get(name);
    }

    // "gib mir die Bean vom Typ ProductService"
    @SuppressWarnings("unchecked")
    public <T> T getBean(Class<T> requiredType) {
        var name = this.beanDefs.stream()
                .filter(def -> def.satisfies(requiredType))
                .map(BeanDefinition::name)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("No bean of type " + requiredType.getName()));
        return (T) getBean(name);
    }
}
