package com.example.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Embedded H2 without Spring Boot.
 * <p>
 * {@link EmbeddedDatabaseBuilder} (from spring-jdbc) creates an in-memory H2
 * database AND runs the given scripts as part of building it — so there is no
 * separate initializer to wire up. The returned bean implements
 * {@link org.springframework.jdbc.datasource.embedded.EmbeddedDatabase}, whose
 * {@code shutdown()} is called automatically when the Spring context closes.
 * <p>
 * Ideal for tests and self-contained demos. Trade-off: the database is
 * ephemeral (in-memory) and disappears with the context.
 */
@Configuration
public class EmbeddedDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")   // DDL — CREATE TABLE ...
                .addScript("classpath:data.sql")     // optional seed data
                .build();
    }
}
