package com.example.aop;

import org.springframework.aop.framework.ProxyFactory;
import pizza.customer.CustomerService;

public class AopDemoApp {

    public static void main(String[] args) {
        ProxyFactory proxyFactory = new ProxyFactory(new CustomerService());
        proxyFactory.addAdvice(new DumpOutputAdvice());
        CustomerService proxy = (CustomerService) proxyFactory.getProxy();
        proxy.getAllCustomers();
    }
}
