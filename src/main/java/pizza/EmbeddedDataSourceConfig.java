package pizza;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;

/**
 * Embedded ephemeral (in-memory) H2 without Spring Boot.
 * <p>
 * Ideal for tests and self-contained demos. 
 */
@Configuration
public class EmbeddedDataSourceConfig {

    @Bean
    public DataSource dataSource() {
        return new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")  // defines schema (tables etc.)
//                .addScript("classpath:data.sql")    // optional seed data
                .build();
    }

}
