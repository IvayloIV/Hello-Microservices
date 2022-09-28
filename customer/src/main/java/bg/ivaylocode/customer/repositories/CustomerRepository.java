package bg.ivaylocode.customer.repositories;

import bg.ivaylocode.customer.entities.Customer;

public interface CustomerRepository {
    boolean save(Customer customer);
}
