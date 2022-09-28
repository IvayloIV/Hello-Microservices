package bg.ivaylocode.customer.controllers;

import bg.ivaylocode.customer.entities.Customer;
import bg.ivaylocode.customer.requests.CustomerRequest;
import bg.ivaylocode.customer.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping
    public Customer saveCustomer(@RequestBody CustomerRequest customerRequest) {
        return customerService.save(customerRequest);
    }
}
