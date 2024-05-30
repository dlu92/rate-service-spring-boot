package org.rates.infrastructure.persistence.rates.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.models.Rate;
import java.util.Date;

public class RateEntityModelTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";

    @Test
    public void testMap() {

        RateEntity rateEntity = new RateEntity(
            new RateId(RATE_ID),
            new RateBrandId(BRAND_ID),
            new RateProductId(PRODUCT_ID),
            new RateStartDate(START_DATE),
            new RateEndDate(END_DATE),
            new RatePrice(PRICE),
            new RateCurrencyCode(CURRENCY_CODE),
            null,
            null
        );

        Rate rate = RateEntityToModel.map(rateEntity, new Rate());

        assert(rateEntity.getBrandId().getValue().equals(rate.getBrandId()));
        assert(rateEntity.getProductId().getValue().equals(rate.getProductId()));
        assert(rateEntity.getStartDate().getValue().equals(rate.getStartDate()));
        assert(rateEntity.getEndDate().getValue().equals(rate.getEndDate()));
        assert(rateEntity.getPrice().getValue().equals(rate.getPrice()));
        assert(rateEntity.getCurrencyCode().getValue().equals(rate.getCurrencyCode()));

    }
}
