package pizza.customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    //
    // constructors and setup
    //

    /**
     * Without a database, e.g. for the slide examples of the early chapters.
     */
    public CustomerService() {
        this(new HashMapCustomerRepository());
    }

    /**
     * With more than one constructor, Spring needs to be told which one to use.
     */
    @Autowired
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    //
    // business logic
    //

    public Customer getCustomerByPhoneNumber(String phoneNumber) {
        return this.customerRepository
                .findByPhoneNumber(phoneNumber)
                .orElseThrow(() -> new CustomerNotFoundException("For phoneNumber `" + phoneNumber + "`"));
    }

    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

}
