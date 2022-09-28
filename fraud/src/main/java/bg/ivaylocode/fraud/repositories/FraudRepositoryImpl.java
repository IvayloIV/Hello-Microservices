package bg.ivaylocode.fraud.repositories;

import bg.ivaylocode.fraud.entities.FraudCheckHistory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class FraudRepositoryImpl implements FraudRepository {

    private List<FraudCheckHistory> histories = new ArrayList<>();

    @Override
    public boolean checkFraudster(FraudCheckHistory fraudCheckHistory) {
        histories.add(fraudCheckHistory);
        return true;
    }
}
