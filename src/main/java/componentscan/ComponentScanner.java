package componentscan;

import java.util.ArrayList;
import java.util.List;

/**
 * A minimal, hand-written component scanner. It inspects a given set of candidate classes and
 * reports the name of every class that is annotated with {@link Component}.
 * <p>
 * This mimics – on a very small scale – what Spring does with {@code @ComponentScan}. The big
 * simplification: instead of searching a package on the classpath, we hand the candidate classes
 * to {@link #scan(Class[])} directly.
 */
public class ComponentScanner {

    /**
     * Scans the given candidate classes for the {@link Component} annotation, prints the name of
     * every component found and returns those names.
     *
     * @return the resolved component names, in scan order
     */
    public List<String> scan(Class<?>... candidateClasses) {
        List<String> componentNames = new ArrayList<>();
        for (Class<?> candidate : candidateClasses) {
            if (candidate.isAnnotationPresent(Component.class)) {
                String name = resolveName(candidate);
                componentNames.add(name);
                System.out.printf("Found component: %-28s -> bean name '%s'%n", candidate.getSimpleName(), name);
            }
        }
        System.out.println(componentNames.size() + " component(s) found.");
        return componentNames;
    }

    /**
     * Resolves the component name: the explicit {@link Component#name()} if set, otherwise the
     * decapitalized simple class name (e.g. {@code ProductService -> productService}).
     */
    private String resolveName(Class<?> componentClass) {
        String explicitName = componentClass.getAnnotation(Component.class).name();
        if (!explicitName.isBlank()) {
            return explicitName;
        }
        String simpleName = componentClass.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }
}
