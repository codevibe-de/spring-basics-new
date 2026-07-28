package pizza.util;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

/**
 * Matches only when a {@link CliArgs} bean has been registered in the context.
 * <p>
 * This is the hand-written equivalent of Spring Boot's {@code @ConditionalOnBean(CliArgs.class)}.
 * <p>
 * ÜBUNG b.3): Diese Klasse ist fertig vorgegeben. Du musst sie nur noch anwenden, indem du
 * den {@code LogicRunner} mit {@code @Conditional(CliArgsPresentCondition.class)} annotierst.
 */
public class CliArgsPresentCondition implements Condition {

    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        var beanFactory = context.getBeanFactory();
        return beanFactory != null
                && beanFactory.getBeanNamesForType(CliArgs.class, true, false).length > 0;
    }
}
