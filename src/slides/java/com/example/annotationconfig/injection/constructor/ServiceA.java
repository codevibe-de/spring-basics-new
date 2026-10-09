// Folie 84 – Constructor Injection - Besonderheiten (@Lazy)
package com.example.annotationconfig.injection.constructor;

import org.springframework.stereotype.Service;

@Service
public class ServiceA {

    private final ServiceB serviceB;

    public ServiceA(ServiceB serviceB) {
        this.serviceB = serviceB;
    }
}
