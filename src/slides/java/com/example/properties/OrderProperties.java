// Folie 158 – Optional C: Konfigurationsklassen
package com.example.properties;

import java.util.List;

public record OrderProperties(
        Integer deliveryTimeInMinutes,
        List<String> discountDays,
        Double discountRate) {
}
