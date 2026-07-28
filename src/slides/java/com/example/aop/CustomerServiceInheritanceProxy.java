package com.example.aop;

import pizza.customer.Customer;
import pizza.customer.CustomerService;

import java.util.List;

public class CustomerServiceInheritanceProxy extends CustomerService {

    @Override
    public List<Customer> getAllCustomers() {
        // Trx hier öffnen
        // ...

        // Eigentliche Call an's Target
        var result = super.getAllCustomers();

        // Trx schließen
        //  ...

        return result;
    }

}
