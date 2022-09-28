package bg.ivaylocode.fraud.repositories;

import bg.ivaylocode.fraud.entities.FraudCheckHistory;

public interface FraudRepository {

    boolean checkFraudster(FraudCheckHistory fraudCheckHistory);
}
