// Folie 88 – Field Injection
package com.example.annotationconfig.injection.field;

import org.springframework.beans.factory.annotation.Autowired;
import pizza.customer.CustomerService;

public abstract class AbstractIT {

    @Autowired
    protected CustomerService customerService;
}
