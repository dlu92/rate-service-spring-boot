package org.rates.infrastructure.persistence.rates.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.models.Rate;
import java.util.Date;
import static junit.framework.Assert.assertEquals;

public class RateEntityModelTest {

    @Test
    public void testMap() {
        RateEntity rateEntity = new RateEntity(
            new RateId(1L),
            new RateBrandId(1),
            new RateProductId(1),
            new RateStartDate(new Date()),
            new RateEndDate(new Date()),
            new RatePrice(1.0f),
            new RateCurrencyCode("USD"),
            null,
            null
        );

        Rate rate = RateEntityToModel.map(rateEntity, new Rate());

        assertEquals(rate.getBrandId(), rateEntity.getBrandId().getValue());
        assertEquals(rate.getProductId(), rateEntity.getProductId().getValue());
        assertEquals(rate.getStartDate(), rateEntity.getStartDate().getValue());
        assertEquals(rate.getEndDate(), rateEntity.getEndDate().getValue());
        assertEquals(rate.getPrice(), rateEntity.getPrice().getValue());
        assertEquals(rate.getCurrencyCode(), rateEntity.getCurrencyCode().getValue());
    }
}
