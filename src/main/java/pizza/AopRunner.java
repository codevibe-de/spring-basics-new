package pizza;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.product.ProductService;
import pizza.util.CommandLineRunner;

/**
 * Dedizierter {@link CommandLineRunner}, in dem der AOP-Proxy erzeugt und ausgeführt wird.
 * Läuft per {@link Order} nach dem {@code LogicRunner}, damit bereits Produkte geladen sind.
 */
@Component
@Order(2)
public class AopRunner implements CommandLineRunner {

    private ProductService productService;  // TODO needs injection

    public AopRunner() {
    }

    @Override
    public void run(String... args) {
        // TODO Erzeugen Sie eine ProxyFactoryBean und setzen Sie die productService-Bean als Target.

        // TODO Fügen Sie den TraceBeforeMethodAdvice und den ProfilingInterceptor via addAdvice(...) hinzu.

        // TODO Holen Sie den Proxy über getObject() und rufen Sie eine Methode auf ihm auf
        //      (z.B. getProduct("P-10")), damit die Aspekte zur Ausführung kommen.
    }

}
