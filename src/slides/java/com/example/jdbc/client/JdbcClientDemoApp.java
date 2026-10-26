// Folie „Ausblick: JdbcClient“, hinter 203 (Deck mit 290 Folien) – Demo
package com.example.jdbc.client;

import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import pizza.customer.Customer;

/**
 * Inserts two customers with DB-generated ids and reads them back via named parameters.
 */
public class JdbcClientDemoApp {

    public static void main(String[] args) {
        EmbeddedDatabase db = new EmbeddedDatabaseBuilder()
                .setType(EmbeddedDatabaseType.H2)
                .addScript("classpath:schema.sql")
                .build();
        try {
            var repository = new JdbcClientCustomerRepository(db);
            Customer anna = repository.save(new Customer("Anna Muster", null, "0931-111"));
            Customer bert = repository.save(new Customer("Bert Beispiel", null, "0931-222"));
            System.out.println("generated ids  -> " + anna.getId() + ", " + bert.getId());
            System.out.println("count          -> " + repository.count());
            System.out.println("by phone       -> " + repository.findByPhoneNumber("0931-222"));
            System.out.println("unknown phone  -> " + repository.findByPhoneNumber("0000"));
        } finally {
            db.shutdown();
        }
    }
}
