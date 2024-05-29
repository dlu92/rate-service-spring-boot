package org.rates.api.restfull.rates.v1.mappers;

import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.domain.rates.RateEntity;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public final class RateEntityCollectionToResponse {

    public static RateResponse[] map(RateEntity[] rates) {
        return Arrays.stream(rates).map(RateEntityToResponse::map).toArray(RateResponse[]::new);
    }
}
