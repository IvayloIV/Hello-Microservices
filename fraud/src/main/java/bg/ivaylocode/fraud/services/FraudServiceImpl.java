package bg.ivaylocode.fraud.services;

import bg.ivaylocode.clients.fraud.FraudResponse;
import bg.ivaylocode.fraud.entities.FraudCheckHistory;
import bg.ivaylocode.fraud.repositories.FraudRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class FraudServiceImpl implements FraudService {

    private final Logger logger;
    private final FraudRepository fraudRepository;

    @Autowired
    public FraudServiceImpl(FraudRepository fraudRepository) {
        this.fraudRepository = fraudRepository;
        logger = LoggerFactory.getLogger(getClass());
    }

    @Override
    public FraudResponse checkFraudster(Long customerId) {
        boolean isFraudster = false;
        long id = ThreadLocalRandom.current().nextLong(1,Long.MAX_VALUE);

        FraudCheckHistory checkHistory = FraudCheckHistory.builder()
            .id(id)
            .customerId(customerId)
            .date(LocalDateTime.now())
            .isFraudster(isFraudster)
            .build();

        fraudRepository.checkFraudster(checkHistory);
        logger.info("Customer with id {} is not fraudster.", customerId);

        return FraudResponse.builder()
            .customerId(customerId)
            .isFraudster(isFraudster)
            .build();
    }
}
