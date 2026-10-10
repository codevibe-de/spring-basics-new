package pizza;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pizza.util.CommandLineRunner;

@Configuration
@ComponentScan
@EnableAspectJAutoProxy
public class PizzaApp {

    public static void main(String[] args) {
        // Instantiate annotation configured context ---
        try (var beanContainer = new AnnotationConfigApplicationContext(PizzaApp.class)) {
            // get runners and run them
            beanContainer.getBeanProvider(CommandLineRunner.class)
                    .orderedStream()
                    .forEach(runner -> runner.run(args));
        }
    }

}
