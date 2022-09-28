package bg.ivaylocode.clients.fraud;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "fraud", path = "/api/v1/fraud")
public interface FraudClient {

    @GetMapping("{id}")
    FraudResponse isFraudster(@PathVariable Long id);
}
