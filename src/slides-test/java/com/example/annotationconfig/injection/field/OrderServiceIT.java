// Folie 88 – Field Injection
package com.example.annotationconfig.injection.field;

import org.springframework.beans.factory.annotation.Autowired;
import pizza.order.OrderService;

public class OrderServiceIT extends AbstractIT {

    @Autowired
    protected OrderService orderService;
}
