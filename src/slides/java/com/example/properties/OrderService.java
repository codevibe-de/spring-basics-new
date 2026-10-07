// Folie 153 – Option A: Inject einzelner Konfigurationswerte
package com.example.properties;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Value("${app.order.delivery-time-in-minutes}")
    private Integer deliveryTimeInMinutes;

    @Value("${app.order.discount-days:}")
    private List<String> discountDays;

    @Value("${app.order.discount-rate:0.0}")
    private double discountRate;
}
