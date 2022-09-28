package bg.ivaylocode.clients.fraud;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FraudResponse {

    private Long customerId;

    private Boolean isFraudster;
}
