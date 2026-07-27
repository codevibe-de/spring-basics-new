package summer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Keeps track of every bean and its (still) unfulfilled dependencies. It hands out beans one at a
 * time via {@link #getNextBeanName()} in an order where a bean is only returned once all of the
 * beans it depends on have already been returned. This provides a valid creation order for the
 * {@link BeanContainer}.
 */
public class BeanDependencyGraph {

    private final Map<String, Set<String>> unfulfilledDependencies = new HashMap<>();

    /**
     * Adds a bean together with the names of the beans it depends on.
     */
    void addBean(String beanName, Set<String> dependencyBeanNames) {
        this.unfulfilledDependencies.put(beanName, new HashSet<>(dependencyBeanNames));
    }

    /**
     * Returns the name of a bean that has no unfulfilled dependencies left and removes it from the
     * graph. Removing it also marks it as fulfilled for every other bean that depends on it, so
     * those beans move closer to becoming returnable themselves.
     *
     * @return a bean name whose dependencies are all fulfilled, or {@code null} if none exists
     * (either because all beans have already been handed out, or because the remaining beans form a
     * circular dependency)
     */
    public String getNextBeanName() {
        String nextBeanName = this.unfulfilledDependencies.entrySet().stream()
                .filter(entry -> entry.getValue().isEmpty())
                .map(Map.Entry::getKey)
                .findFirst()
                .orElse(null);
        if (nextBeanName != null) {
            this.unfulfilledDependencies.remove(nextBeanName);
            this.unfulfilledDependencies.values().forEach(deps -> deps.remove(nextBeanName));
        }
        return nextBeanName;
    }

    /**
     * Returns {@code true} once every bean has been handed out via {@link #getNextBeanName()}.
     */
    boolean isEmpty() {
        return this.unfulfilledDependencies.isEmpty();
    }
}
