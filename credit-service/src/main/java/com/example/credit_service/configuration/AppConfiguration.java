package com.example.credit_service.configuration;

import com.example.credit_service.repository.BatchCreditRepository;
import com.example.credit_service.service.CreditService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfiguration {
    @Bean
    public CreditService creditService(BatchCreditRepository batchCreditRepository) {
        return new CreditService(batchCreditRepository);
    }
}
