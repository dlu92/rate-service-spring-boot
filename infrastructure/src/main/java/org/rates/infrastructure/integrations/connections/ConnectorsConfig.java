package org.rates.infrastructure.integrations.connections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConnectorsConfig {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
