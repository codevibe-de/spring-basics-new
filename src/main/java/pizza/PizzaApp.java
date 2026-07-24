package pizza;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import pizza.aop.ProfilingInterceptor;
import pizza.aop.TraceBeforeMethodAdvice;
import pizza.product.ProductService;
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
            // Bestehende Bean holen und mit einem AOP-Proxy umwickeln, der die Aufrufe
            // zunächst trace't und anschließend deren Ausführungsdauer misst.
            var productService = beanContainer.getBean(ProductService.class);

            var proxyFactory = new ProxyFactoryBean();
            proxyFactory.setTarget(productService);
            proxyFactory.addAdvice(new TraceBeforeMethodAdvice());
            proxyFactory.addAdvice(new ProfilingInterceptor());
            var tracedProductService = (ProductService) proxyFactory.getObject();

            // Jeder Aufruf läuft nun durch die Advices:
            System.out.println("\n--- AOP demonstration ---");
            tracedProductService.getAllProducts();
            tracedProductService.getProduct("P-10");
        }
    }

}