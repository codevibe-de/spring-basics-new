package pizza.order;

import java.util.List;

public record OrderProperties(
        Integer deliveryTimeInMinutes,
        List<String> discountDays,
        Double discountRate
) {
}