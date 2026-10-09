// Folie 84 – Constructor Injection - Besonderheiten (@Lazy)
package com.example.annotationconfig.injection.constructor;

import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
public class ServiceB {

    private final ServiceA serviceA;

    public ServiceB(@Lazy ServiceA serviceA) {
        this.serviceA = serviceA;
    }
}
