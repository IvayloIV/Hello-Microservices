package bg.ivaylocode.fraud.entities;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FraudCheckHistory {

    private Long id;

    private Long customerId;

    private LocalDateTime date;

    private Boolean isFraudster;
}
