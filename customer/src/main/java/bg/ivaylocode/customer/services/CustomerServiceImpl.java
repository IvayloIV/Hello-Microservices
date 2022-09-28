package bg.ivaylocode.customer.services;

import bg.ivaylocode.clients.fraud.FraudClient;
import bg.ivaylocode.clients.fraud.FraudResponse;
import bg.ivaylocode.clients.notification.NotificationClient;
import bg.ivaylocode.clients.notification.NotificationRequest;
import bg.ivaylocode.customer.entities.Customer;
import bg.ivaylocode.customer.repositories.CustomerRepository;
import bg.ivaylocode.customer.requests.CustomerRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final Logger logger;
    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;

    private final FraudClient fraudClient;
    private final NotificationClient notificationClient;

    private final CircuitBreakerFactory circuitBreakerFactory;

    private CircuitBreaker circuitBreaker;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository,
                               RestTemplate restTemplate,
                               FraudClient fraudClient,
                               NotificationClient notificationClient,
                               CircuitBreakerFactory circuitBreakerFactory) {
        logger = LoggerFactory.getLogger(getClass());
        this.customerRepository = customerRepository;
        this.restTemplate = restTemplate;
        this.fraudClient = fraudClient;
        this.notificationClient = notificationClient;
        this.circuitBreakerFactory = circuitBreakerFactory;
    }

    @PostConstruct
    public void init() {
        circuitBreaker = circuitBreakerFactory.create("fraud-service");
    }

    @Override
    public Customer save(CustomerRequest customerRequest) {
        long id = ThreadLocalRandom.current().nextLong(1,Long.MAX_VALUE);

        Customer customer = Customer.builder()
            .id(id)
            .name(customerRequest.getName())
            .age(customerRequest.getAge())
            .build();

        boolean isSaved = customerRepository.save(customer);
        if (isSaved) {
            logger.info("Customer was successfully saved: {}", customer);
            logger.info("OpenFeign - {}", fraudClient);
            FraudResponse fraudResponse = circuitBreaker.run(
                    () -> fraudClient.isFraudster(customer.getId()),
                    tx -> new FraudResponse());
//            FraudResponse fraudResponse = restTemplate
//                    .getForObject("http://FRAUD/api/v1/fraud/{id}", FraudResponse.class, customer.getId());
            logger.info("Customer with id {} is fraudster: {}", fraudResponse.getCustomerId(), fraudResponse.getIsFraudster());

            NotificationRequest notificationRequest = NotificationRequest.builder()
                .message(String.format("Customer with id %s was successfully registered.", id))
                .receiver(customer.getName() + "@gmail.com")
                .build();
            notificationClient.send(notificationRequest);
            return customer;
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE);
        }
    }
}
