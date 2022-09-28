package bg.ivaylocode.customer.services;

import bg.ivaylocode.customer.entities.Customer;
import bg.ivaylocode.customer.requests.CustomerRequest;

public interface CustomerService {
    Customer save(CustomerRequest customer);
}
