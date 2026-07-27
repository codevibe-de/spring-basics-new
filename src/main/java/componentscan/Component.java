package componentscan;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Marks a class as a "component" that our {@link ComponentScanner} should detect.
 * <p>
 * This is a hand-written stand-in for Spring's {@code org.springframework.stereotype.Component},
 * built to practice custom annotations and reflection before Spring takes over in lesson 025.
 */
@Retention(RetentionPolicy.RUNTIME)     // must survive until runtime so reflection can read it
@Target(ElementType.TYPE)               // may only be placed on classes
public @interface Component {

    /**
     * Optional component name. If left empty, the {@link ComponentScanner} derives a name from
     * the class name – just like Spring does with {@code @Component}.
     */
    String name() default "";
}
