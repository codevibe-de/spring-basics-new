package com.example.jdbc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.DataSourceInitializer;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;

import javax.sql.DataSource;

/**
 * External (TCP) H2 without Spring Boot.
 * <p>
 * The connection settings are externalized under the same {@code spring.datasource.*}
 * keys Spring Boot uses — but with no Boot on the classpath we read them from the
 * {@link Environment} and build the {@link DataSource} ourselves. Under Spring Boot
 * this whole class disappears: keep the four properties, delete the wiring.
 */
@Configuration
@PropertySource("classpath:application.properties")
public class ExternalDataSourceConfig {

    @Bean
    public DataSourceProperties dataSourceProperties(Environment env) {
        return new DataSourceProperties(
                env.getProperty("spring.datasource.driver-class-name", "org.h2.Driver"),
                env.getProperty("spring.datasource.url"),
                env.getProperty("spring.datasource.username", "sa"),
                env.getProperty("spring.datasource.password", "")
        );
    }

    @Bean
    public DataSource dataSource(DataSourceProperties props) {
        var ds = new DriverManagerDataSource();
        ds.setDriverClassName(props.driverClassName());
        ds.setUrl(props.url());
        ds.setUsername(props.username());
        ds.setPassword(props.password());
        return ds;
    }

    @Bean
    public DataSourceInitializer dataSourceInitializer(DataSource dataSource) {
        var populator = new ResourceDatabasePopulator();
        populator.addScript(new ClassPathResource("schema.sql"));

        var initializer = new DataSourceInitializer();
        initializer.setDataSource(dataSource);
        initializer.setDatabasePopulator(populator);
        return initializer;
    }

}
