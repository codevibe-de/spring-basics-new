// Folie 123 – Beispiel JDK-Proxy
// Folie 121 – InvocationHandler (Interface aus java.lang.reflect)
package com.example.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

class AspectInvocationHandler implements InvocationHandler {

    private Object obj;

    public AspectInvocationHandler(Object obj) {
        this.obj = obj;
    }

    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("Before invoking the method");
        Object result = method.invoke(obj, args);
        System.out.println("After invoking the method");
        return result;
    }
}
