// Folie 117 – Aspekte mit Annotationen
package com.example.aop.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Around("execution(* pizza.customer.CustomerService.*(..))")
    public Object log(ProceedingJoinPoint joinPoint)
            throws Throwable {
        String name = joinPoint.getSignature().getName();
        System.out.println("-> " + name);
        Object result = joinPoint.proceed();
        System.out.println("<- " + name + ": " + result);
        return result;
    }
}
