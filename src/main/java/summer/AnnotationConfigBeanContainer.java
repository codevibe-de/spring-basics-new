package summer;

/**
 * A {@link BeanContainer} that is configured through annotations instead of an XML file.
 * <p>
 * Where the {@link XmlBeanContainer} reads its bean definitions from a {@code beans.xml}, this
 * container inspects a set of candidate classes and registers every class that is marked with
 * {@link Component} as a bean. This is exactly the idea behind Spring's
 * {@code AnnotationConfigApplicationContext} together with {@code @ComponentScan} – only
 * simplified: instead of scanning a package on the classpath, we hand the candidate classes to
 * the constructor directly.
 *
 * <pre>{@code
 * var container = new AnnotationConfigBeanContainer(
 *         HashMapProductRepository.class,
 *         ProductService.class,
 *         CustomerService.class,
 *         OrderService.class);
 * ProductService service = container.getBean(ProductService.class);
 * }</pre>
 *
 * @see Component
 * @see XmlBeanContainer
 */
public class AnnotationConfigBeanContainer extends BeanContainer {

    public AnnotationConfigBeanContainer(Class<?>... candidateClasses) {
        this.scan(candidateClasses);
        this.refresh();
    }

    /**
     * Registers every candidate class annotated with {@link Component} as a bean definition.
     */
    private void scan(Class<?>[] candidateClasses) {
        for (Class<?> candidate : candidateClasses) {
            if (candidate.isAnnotationPresent(Component.class)) {
                defineBean(determineBeanName(candidate), candidate);
            }
        }
    }

    /**
     * Determines the bean name: the explicit {@link Component#value()} if given, otherwise the
     * decapitalized simple class name (e.g. {@code ProductService -> productService}).
     */
    private String determineBeanName(Class<?> beanClass) {
        String explicitName = beanClass.getAnnotation(Component.class).value();
        if (!explicitName.isBlank()) {
            return explicitName;
        }
        String simpleName = beanClass.getSimpleName();
        return Character.toLowerCase(simpleName.charAt(0)) + simpleName.substring(1);
    }
}
