package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import pizza.order.OrderProperties;

import java.util.List;

@Configuration
@PropertySource("classpath:application.properties")
public class PropertiesConfig {

    @Bean
    OrderProperties orderProperties(Environment env) {
        return new OrderProperties(
                env.getProperty("app.order.delivery-time-in-minutes", Integer.class, 30),
                env.getProperty("app.order.discount-days", List.class, List.of()),
                env.getProperty("app.order.discount-rate", Double.class, 0.0)
        );
    }

}
