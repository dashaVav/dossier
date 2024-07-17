package com.example.dossier.services.client;

import feign.Retryer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DealClientConfig {
    @Value("${feign-client.deal-client.retryer.period}")
    private long period;

    @Value("${feign-client.deal-client.retryer.max-period}")
    private long maxPeriod;

    @Value("${feign-client.deal-client.retryer.max-attempts}")
    private int maxAttempts;

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(period, maxPeriod, maxAttempts);
    }
}
