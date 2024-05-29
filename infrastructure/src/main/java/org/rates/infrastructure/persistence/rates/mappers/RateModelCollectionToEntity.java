package org.rates.infrastructure.persistence.rates.mappers;

import org.rates.domain.rates.RateEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
public final class RateModelCollectionToEntity {

    public static RateEntity[] map(Rate[] rates) {
        return Arrays.stream(rates)
            .map(RateModelToEntity::map)
            .toArray(RateEntity[]::new);
    }

}
