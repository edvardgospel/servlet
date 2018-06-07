package com.ubb.web.labs.lab6.configuration;

import com.ubb.web.labs.lab6.service.StatisticsManagerService;
import com.ubb.web.labs.lab6.service.ValidatorService;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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


}
