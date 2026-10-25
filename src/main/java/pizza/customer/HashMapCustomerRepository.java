package pizza.customer;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

public class HashMapCustomerRepository implements CustomerRepository {

    private final Map<Long, Customer> customersMap = new LinkedHashMap<>();
    private final AtomicLong idSequence = new AtomicLong(1);

    @Override
    public Customer save(Customer customer) {
        customer.setId(idSequence.getAndIncrement());
        customersMap.put(customer.getId(), customer);
        return customer;
    }

    @Override
    public List<Customer> findAll() {
        return customersMap.values().stream().toList();
    }

    @Override
    public Optional<Customer> findByPhoneNumber(String phoneNumber) {
        return customersMap.values().stream()
                .filter(c -> phoneNumber.equals(c.getPhoneNumber()))
                .findFirst();
    }
}
