package org.rates.infrastructure.persistence.rates.mappers;

import org.rates.domain.rates.RateEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.springframework.stereotype.Component;

@Component
public final class RateEntityToModel {

    public static Rate map(RateEntity rateEntity, Rate rate) {
        rate.setBrandId(rateEntity.getBrandId().getValue());
        rate.setProductId(rateEntity.getProductId().getValue());
        rate.setStartDate(rateEntity.getStartDate().getValue());
        rate.setEndDate(rateEntity.getEndDate().getValue());
        rate.setPrice(rateEntity.getPrice().getValue());
        rate.setCurrencyCode(rateEntity.getCurrencyCode().getValue());

        return rate;
    }

}
