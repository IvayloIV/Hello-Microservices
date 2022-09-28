package bg.ivaylocode.fraud.services;

import bg.ivaylocode.clients.fraud.FraudResponse;

public interface FraudService {
    public FraudResponse checkFraudster(Long customerId);
}
