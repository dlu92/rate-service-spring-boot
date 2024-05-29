package org.rates.infrastructure.integrations.connections;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Component
@AllArgsConstructor
public final class JsonApiConnection {

    private final RestTemplate restTemplate;

    public <T> T send(String url, @Nullable Object request, HttpMethod method, Class<T> entity) {

        HttpHeaders headers = new HttpHeaders();

        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<Object> connector = new HttpEntity<>(request, headers);

        ResponseEntity<T> response = restTemplate.exchange(url, method, connector, entity);

        return response.getBody();

    }
}
