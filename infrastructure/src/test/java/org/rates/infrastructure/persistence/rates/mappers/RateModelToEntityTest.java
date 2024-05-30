package org.rates.infrastructure.persistence.rates.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.rates.RateEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;

import java.util.Date;

public class RateModelToEntityTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";

    @Test
    public void testMap() {

        Rate rate = new Rate();

        rate.setId(RATE_ID);
        rate.setBrandId(BRAND_ID);
        rate.setProductId(PRODUCT_ID);
        rate.setStartDate(START_DATE);
        rate.setEndDate(END_DATE);
        rate.setPrice(PRICE);
        rate.setCurrencyCode(CURRENCY_CODE);

        RateEntity rateEntity = RateModelToEntity.map(rate);

        assert(rateEntity.getBrandId().getValue().equals(rate.getBrandId()));
        assert(rateEntity.getProductId().getValue().equals(rate.getProductId()));
        assert(rateEntity.getStartDate().getValue().equals(rate.getStartDate()));
        assert(rateEntity.getEndDate().getValue().equals(rate.getEndDate()));
        assert(rateEntity.getPrice().getValue().equals(rate.getPrice()));
        assert(rateEntity.getCurrencyCode().getValue().equals(rate.getCurrencyCode()));

    }
}
