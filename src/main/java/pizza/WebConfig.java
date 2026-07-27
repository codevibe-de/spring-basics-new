package pizza;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Enables Spring MVC (annotation-driven controllers, message converters, etc.).
 * Picked up automatically by {@link PizzaApp}'s {@code @ComponentScan}.
 */
@Configuration
@EnableWebMvc
public class WebConfig {
}
