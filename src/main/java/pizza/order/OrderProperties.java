package pizza.order;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;

/**
 * Type-safe binding of the {@code app.order.*} properties from
 * {@code application.properties} (constructor binding on a record).
 *
 * <p>Registered via {@code @ConfigurationPropertiesScan} on {@code PizzaApp};
 * Boot builds the bean and injects it wherever an {@code OrderProperties} is required
 * (e.g. {@code OrderService}). Replaces the former hand-written {@code @Bean} in
 * {@code PropertiesConfig} that read each value from the {@code Environment}.
 */
@ConfigurationProperties(prefix = "app.order")
public record OrderProperties(
        Integer deliveryTimeInMinutes,
        List<String> discountDays,
        Double discountRate
) {
}
