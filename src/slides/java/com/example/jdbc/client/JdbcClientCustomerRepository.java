// Folie „Ausblick: JdbcClient“, hinter 203 (Deck mit 290 Folien)
package com.example.jdbc.client;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import pizza.customer.Address;
import pizza.customer.Customer;

import javax.sql.DataSource;
import java.util.Optional;

/**
 * Slide version of {@code pizza.customer.JdbcCustomerRepository} from {@code 045-jdbc-solution},
 * shortened to name and phone number so that it fits the slide.
 */
public class JdbcClientCustomerRepository {

    static final String SELECT_BY_PHONE_SQL =
            "SELECT * FROM customers WHERE phone_number = :phoneNumber";

    private final JdbcClient jdbcClient;
    private final RowMapper<Customer> rowMapper = (rs, rowNum) -> new Customer(
            rs.getLong("id"),
            rs.getString("full_name"),
            new Address(rs.getString("street"), rs.getString("postal_code"), rs.getString("city")),
            rs.getString("phone_number"));

    public JdbcClientCustomerRepository(DataSource dataSource) {
        this.jdbcClient = JdbcClient.create(dataSource);
    }

    public Customer save(Customer customer) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        this.jdbcClient.sql("""
                INSERT INTO customers (full_name, phone_number)
                VALUES (:fullName, :phoneNumber)""")
                .param("fullName", customer.getFullName())
                .param("phoneNumber", customer.getPhoneNumber())
                .update(keyHolder, "id");
        customer.setId(keyHolder.getKeyAs(Long.class));
        return customer;
    }

    public Optional<Customer> findByPhoneNumber(String phone) {
        return this.jdbcClient.sql(SELECT_BY_PHONE_SQL)
                .param("phoneNumber", phone)
                .query(this.rowMapper)
                .optional();
    }

    public int count() {
        return this.jdbcClient.sql("SELECT COUNT(*) FROM customers")
                .query(Integer.class)
                .single();
    }
}
