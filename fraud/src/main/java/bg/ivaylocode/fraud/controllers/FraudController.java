package bg.ivaylocode.fraud.controllers;

import bg.ivaylocode.clients.fraud.FraudResponse;
import bg.ivaylocode.fraud.services.FraudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/fraud")
public class FraudController {

    private final FraudService fraudService;

    @Autowired
    public FraudController(FraudService fraudService) {
        this.fraudService = fraudService;
    }

    @GetMapping("{id}")
    public FraudResponse isFraudster(@PathVariable Long id) {
        return fraudService.checkFraudster(id);
    }
}
