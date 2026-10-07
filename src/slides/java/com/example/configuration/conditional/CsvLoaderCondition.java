// Folie „Bedingte Bean-Definitionen“ (030) – @Conditional mit eigener Condition
package com.example.configuration.conditional;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

public class CsvLoaderCondition
        implements Condition {

    @Override
    public boolean matches(
            ConditionContext context,
            AnnotatedTypeMetadata metadata
    ) {
        var env = context.getEnvironment();
        var key = "app.data-loader";
        var value = env.getProperty(key);
        return "csv".equals(value);
    }
}
