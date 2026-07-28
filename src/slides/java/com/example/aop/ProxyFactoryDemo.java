package com.example.aop;

import org.springframework.aop.framework.ProxyFactory;
import pizza.customer.CustomerService;

public class ProxyFactoryDemo {

    public static void main(String[] args) {
        var security = new SecurityService();
        var cs = new CustomerService();

        ProxyFactory proxyFactory = new ProxyFactory(cs);
        proxyFactory.addAdvice(new AuthenticationCheckAdvice(security));
        CustomerService csProxy = (CustomerService) proxyFactory.getProxy();

        csProxy.getAllCustomers();
    }
}
