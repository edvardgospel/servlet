package com.ubb.web.labs.lab6.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.ubb.web.labs.lab6.service.NumberGeneratorService;
import com.ubb.web.labs.lab6.service.StatisticsManagerService;
import com.ubb.web.labs.lab6.service.ValidatorService;

@Configuration
@EnableJpaRepositories
@EntityScan("com.ubb.web.labs.lab6.domain")
public class ApplicationConfig {

    @Bean
    public ValidatorService createValidatorService() {
        return new ValidatorService();
    }

    @Bean
    public StatisticsManagerService createStatisticsService() {
        return new StatisticsManagerService();
    }

    @Bean
    public NumberGeneratorService createNumberGeneratorService() { return new NumberGeneratorService(); }
}
