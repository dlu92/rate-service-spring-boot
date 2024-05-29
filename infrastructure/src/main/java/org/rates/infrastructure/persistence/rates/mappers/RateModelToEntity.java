package org.rates.infrastructure.persistence.rates.mappers;

import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.springframework.stereotype.Component;

@Component
public final class RateModelToEntity {

    public static RateEntity map(Rate rate) {

        return new RateEntity(
            new RateId(rate.getId()),
            new RateBrandId(rate.getBrandId()),
            new RateProductId(rate.getProductId()),
            new RateStartDate(rate.getStartDate()),
            new RateEndDate(rate.getEndDate()),
            new RatePrice(rate.getPrice()),
            new RateCurrencyCode(rate.getCurrencyCode()),
            null,
            null
        );
    }

}
