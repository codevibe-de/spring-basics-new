package pizza;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import pizza.util.CommandLineRunner;

public class PizzaApp {

    public static void main(String[] args) {
        // Instantiate XML configured context ---
        try (var beanContainer = new AnnotationConfigApplicationContext(PizzaApp.class.getPackageName())) {
            // get runners and run them
            beanContainer.getBeanProvider(CommandLineRunner.class)
                    .orderedStream()
                    .forEach(runner -> runner.run(args));
        }
    }

}