package componentscan;

import pizza.customer.Address;
import pizza.customer.CustomerService;
import pizza.order.OrderService;
import pizza.product.HashMapProductRepository;
import pizza.product.ProductService;

import java.util.ArrayList;
import java.util.List;

/**
 * A minimal, handwritten component scanner. It inspects a given set of candidate classes and
 * reports the name of every class that is annotated with {@code @Component}.
 */
public class ComponentScanner {

    /**
     * Scans the given candidate classes for the {@code @Component} annotation, prints the name of
     * every component found and returns those names.
     *
     * @return the resolved component names, in scan order
     */
    public List<String> scan(Class<?>... candidateClasses) {
        var result = new ArrayList<String>();
        // TODO
        //  1. für jede Klasse prüfen, ob diese mit @Component annotiert ist
        //  2. Namen mit resolveName(...) ermitteln (siehe unten)
        //  3. gefundene Namen der Ergebnisliste hinzufügen
        //  4. Liste zurückgeben
        throw new UnsupportedOperationException("TODO: ComponentScanner.scan(...) implementieren");
    }

    /**
     * Resolves the component name: the explicit {@link Component#value()} if set, otherwise the
     * decapitalized simple class name (e.g. {@code ProductService -> productService}).
     */
    private String resolveName(Class<?> componentClass) {
        String explicitName = componentClass.getAnnotation(Component.class).value();
        if (!explicitName.isBlank()) {
            return explicitName;
        }
        String simpleName = componentClass.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }

    public static void main(String[] args) {
        new ComponentScanner().scan(
                HashMapProductRepository.class,
                ProductService.class,
                CustomerService.class,
                OrderService.class,
                Address.class
        );
    }
}
