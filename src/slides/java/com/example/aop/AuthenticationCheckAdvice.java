package com.example.aop;

import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

public class AuthenticationCheckAdvice implements MethodBeforeAdvice {

    private final SecurityService securityService;

    AuthenticationCheckAdvice(SecurityService securityService) {
        this.securityService = securityService;
    }

    @Override
    public void before(Method method, Object[] args, Object target) {
        if (!securityService.isUserLoggedIn()) {
            throw new IllegalStateException("Not authenticated");
        }
    }
}


class SecurityService {
    public boolean isUserLoggedIn() {
        return false;
    }
}