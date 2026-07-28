package pizza;

import org.springframework.aop.framework.ProxyFactory;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import pizza.aop.ProfilingInterceptor;
import pizza.aop.TraceBeforeMethodAdvice;
import pizza.product.ProductService;
import pizza.util.CommandLineRunner;

/**
 * Dedizierter {@link CommandLineRunner}, in dem der AOP-Proxy erzeugt und ausgeführt wird.
 * Läuft per {@link Order} nach dem {@code LogicRunner}, damit bereits Produkte geladen sind.
 */
@Component
@Order(2)
public class AopRunner implements CommandLineRunner {

    private ProductService productService;

    public AopRunner(ProductService productService) {
        this.productService = productService;
    }

    @Override
    public void run(String... args) {
        // Bestehende `productService` Bean mit einem AOP-Proxy umwickeln, der die Aufrufe
        // zunächst trace't und anschließend deren Ausführungsdauer misst.
        var proxyFactory = new ProxyFactory();
        proxyFactory.setTarget(productService);
        proxyFactory.addAdvice(new TraceBeforeMethodAdvice());
        proxyFactory.addAdvice(new ProfilingInterceptor());
        ProductService tracedProductService = (ProductService) proxyFactory.getProxy();

        // Jeder Aufruf läuft nun durch die Advices:
        System.out.println("\n--- AOP demonstration ---");
        tracedProductService.getAllProducts();
        tracedProductService.getProduct("P-10");
    }

}
