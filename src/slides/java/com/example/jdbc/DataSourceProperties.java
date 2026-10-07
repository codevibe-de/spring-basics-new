// Keine Folie mit Code – Ergänzung Kapitel 045 (externe H2 per TCP, spring.datasource.*-Keys ohne Boot)
package com.example.jdbc;

/**
 * Holds the externalized datasource settings.
 * <p>
 * Field names mirror Spring Boot's {@code spring.datasource.*} keys — in a Boot
 * app this exact record already exists and is bound for you. Here (plain Spring)
 * we bind it ourselves, see {@link ExternalDataSourceConfig}.
 */
public record DataSourceProperties(
        String driverClassName,
        String url,
        String username,
        String password
) {
}
