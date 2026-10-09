// Folie 91 – Field Injection – optionale Beans
package com.example.annotationconfig.injection.field.optional;

import org.jspecify.annotations.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import pizza.customer.CustomerService;

import java.util.Optional;

public abstract class AbstractIT {

    @Autowired(required = false)
    protected CustomerService customerService;

    @Autowired
    protected Optional<CustomerService> optionalCustomerService;

    @Autowired
    protected @Nullable CustomerService nullableCustomerService;
}
