package summer;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a bean that our {@link AnnotationConfigBeanContainer} should pick up
 * during "component scanning" – the annotation-driven counterpart to a {@code <bean>}
 * entry in the XML used by {@link XmlBeanContainer}.
 * <p>
 * This is a simplified stand-in for Spring's {@code org.springframework.stereotype.Component}.
 *
 * @see AnnotationConfigBeanContainer
 */
@Retention(RetentionPolicy.RUNTIME)     // keep the annotation available at runtime for reflection
@Target(ElementType.TYPE)               // may only be placed on classes
public @interface Component {

    /**
     * The (optional) bean name. If left empty, the container derives the name from the
     * class name – just like Spring does with {@code @Component}.
     */
    String value() default "";
}
