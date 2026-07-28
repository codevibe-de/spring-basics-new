package pizza.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Übung a): zum Bearbeiten diese @Disabled-Annotation entfernen")
// TODO a): Diese Testklasse als Spring-Test konfigurieren
//          (passende Annotation an der Klasse, damit ein ApplicationContext hochgefahren wird).
class CustomerServiceTest {

    // TODO a): den CustomerService aus dem Spring-Context injizieren lassen
    CustomerService customerService;

    @Test
    void createCustomer() {
        // given
        Address address = new Address("123 Main St", "12345", "Anytown");
        Customer customer = new Customer("John Doe", address, "0170-123123123");

        // when
        Customer createdCustomer = customerService.createCustomer(customer);

        // then
        Assertions.assertThat(createdCustomer).isNotNull();
        Assertions.assertThat(createdCustomer.getId()).isNotNull();
    }
}
