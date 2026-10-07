// Folie 40 – Bean-Definitionen verwalten
package com.example.beancontainer;

/**
 * Vereinfachte Bean-Definition: Name und Typ. Die ausführliche Variante steht im
 * Branch 011-beanContainer-solution ({@code summer.BeanDefinition}).
 */
public record BeanDefinition(String name, Class<?> type) {

    public boolean satisfies(Class<?> requiredType) {
        return requiredType.isAssignableFrom(type);
    }
}
