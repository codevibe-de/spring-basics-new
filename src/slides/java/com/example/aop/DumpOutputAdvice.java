package com.example.aop;

import org.jspecify.annotations.Nullable;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class DumpOutputAdvice implements AfterReturningAdvice {

    @Override
    public void afterReturning(@Nullable Object returnValue, Method method, Object[] args, @Nullable Object target) throws Throwable {
        System.out.println("Method " + method.getName() + "() was called and the output is:");
        System.out.println(returnValue);
    }

}
