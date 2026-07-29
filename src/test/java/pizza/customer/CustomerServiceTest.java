package pizza.customer;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

// @SpringBootTest boots the context via SpringApplication (like the real app), so Boot's
// CommandLineRunners — including the DataLoadRunner — actually run. The "test" profile keeps
// the noisy LogicRunner out (see @Profile("!test") on it).
@SpringBootTest
@ActiveProfiles("test")
class CustomerServiceTest {

    @Autowired
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
