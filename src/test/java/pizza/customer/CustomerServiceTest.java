package pizza.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("zum Bearbeiten diese @Disabled-Annotation entfernen")
// TODO Diese Testklasse als Spring-Test konfigurieren, damit ein Context zur Verfügung steht
class CustomerServiceTest {

    // TODO CustomerService aus dem Spring-Context injizieren lassen

    @Test
    void createCustomer() {
        // given
        Address address = new Address("123 Main St", "12345", "Anytown");
        Customer customer = new Customer("John Doe", address, "0170-123123123");

        // when
        Customer createdCustomer = null; // TODO call CustomerService

        // then
        Assertions.assertThat(createdCustomer).isNotNull();
        Assertions.assertThat(createdCustomer.getId()).isNotNull();
    }
}
