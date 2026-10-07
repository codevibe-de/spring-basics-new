// Folie „Bedingte Bean-Definitionen“ (030) – @Conditional mit eigener Condition
package com.example.configuration.conditional;

import org.springframework.context.annotation.Conditional;
import org.springframework.stereotype.Component;

@Component
@Conditional(CsvLoaderCondition.class)
public class CsvDataLoader {
    // ...
}
