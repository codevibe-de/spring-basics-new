// Folie „JdbcTemplate – die wichtigsten Methoden“, hinter 202 (Deck mit 290 Folien)
package com.example.jdbc;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.IncorrectResultSetColumnCountException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Runs every call from the slide's table against an embedded H2 with the course schema.
 * <p>
 * Contrast: a type ({@code Integer.class}, {@code String.class}) maps exactly one column,
 * a {@link RowMapper} maps a whole row to an object, without either you get maps.
 */
public class JdbcTemplateMethodsDemo {

    record Product(String id, String name, BigDecimal price) {
    }

    static final RowMapper<Product> ROW_MAPPER = (rs, rowNum) -> new Product(
            rs.getString("pk"),
            rs.getString("name"),
            rs.getBigDecimal("price"));

    public static void main(String[] args) {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
        try {
            run(new JdbcTemplate(db));
        } finally {
            db.shutdown();
        }
    }

    static void run(JdbcTemplate jdbc) {
        String insert = "INSERT INTO products (pk, name, price) VALUES (?, ?, ?)";

        // update: INSERT, UPDATE, DELETE -> number of affected rows
        int rows = jdbc.update(insert, "P-1", "Margherita", 7.5);
        jdbc.update(insert, "P-2", "Salami", 8.9);
        System.out.println("update               -> " + rows);

        // queryForObject with a type: one column, exactly one row
        Integer count = jdbc.queryForObject(
                "SELECT COUNT(*) FROM products WHERE pk = ?",
                Integer.class, "P-1");
        System.out.println("queryForObject(type) -> " + count);

        // queryForObject with a RowMapper: one row as an object
        Product product = jdbc.queryForObject(
                "SELECT * FROM products WHERE pk = ?",
                ROW_MAPPER, "P-1");
        System.out.println("queryForObject(rm)   -> " + product);

        // queryForList with a type: one column, any number of rows
        List<String> names = jdbc.queryForList(
                "SELECT name FROM products ORDER BY pk",
                String.class);
        System.out.println("queryForList(type)   -> " + names);

        // query with a RowMapper: any number of rows as objects
        List<Product> products = jdbc.query(
                "SELECT * FROM products WHERE price > ?",
                ROW_MAPPER, 8);
        System.out.println("query(rm)            -> " + products);

        // without type or RowMapper: column name -> value
        Map<String, Object> row = jdbc.queryForMap(
                "SELECT * FROM products WHERE pk = ?", "P-2");
        System.out.println("queryForMap          -> " + row);
        List<Map<String, Object>> all = jdbc.queryForList(
                "SELECT * FROM products ORDER BY pk");
        System.out.println("queryForList         -> " + all);

        // queryForObject expects exactly one row
        try {
            jdbc.queryForObject("SELECT * FROM products WHERE pk = ?",
                    ROW_MAPPER, "P-404");
        } catch (EmptyResultDataAccessException e) {
            System.out.println("0 rows               -> " + e.getClass().getSimpleName());
        }
        try {
            jdbc.queryForObject("SELECT * FROM products", ROW_MAPPER);
        } catch (IncorrectResultSizeDataAccessException e) {
            System.out.println("2 rows               -> " + e.getClass().getSimpleName());
        }

        // a type only works for a single column
        try {
            jdbc.queryForObject("SELECT * FROM products WHERE pk = ?",
                    String.class, "P-1");
        } catch (IncorrectResultSetColumnCountException e) {
            System.out.println("type, 3 columns      -> " + e.getClass().getSimpleName());
        }
    }
}
