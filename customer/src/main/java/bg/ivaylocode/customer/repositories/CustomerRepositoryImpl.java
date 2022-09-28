package bg.ivaylocode.customer.repositories;

import bg.ivaylocode.customer.entities.Customer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CustomerRepositoryImpl implements CustomerRepository {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public boolean save(Customer customer) {
        customers.add(customer);
        return true;
    }
}
