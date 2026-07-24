package pizza;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pizza.util.CommandLineRunner;

@Configuration
@ComponentScan
public class PizzaApp {

    public static void main(String[] args) {
        // Instantiate annotation configured context ---
        try (var beanContainer = new AnnotationConfigApplicationContext(PizzaApp.class)) {
            // get runners and run them
            beanContainer.getBeanProvider(CommandLineRunner.class)
                    .orderedStream()
                    .forEach(runner -> runner.run(args));

            // --- Übung 027 a) AOP ---
            // TODO 027 a): Holen Sie sich eine bestehende Bean (z.B. die ProductService-Bean) aus dem
            //              Container und erzeugen Sie mit Springs ProxyFactoryBean einen AOP-Proxy davon.
            //              Fügen Sie Ihr TraceBeforeMethodAdvice und Ihren ProfilingInterceptor hinzu und
            //              rufen Sie dann eine Methode auf dem Proxy auf (z.B. getProduct("P-10")),
            //              damit die Aspekte zur Ausführung kommen.
        }
    }

}