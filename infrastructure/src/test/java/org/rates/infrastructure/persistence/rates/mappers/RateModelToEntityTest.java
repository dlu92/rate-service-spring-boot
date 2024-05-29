package org.rates.infrastructure.persistence.rates.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.rates.RateEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;

import java.util.Date;

import static junit.framework.Assert.assertEquals;

public class RateModelToEntityTest {

    @Test
    public void testMap() {
        Rate rate = new Rate();

        rate.setId(1L);
        rate.setBrandId(1);
        rate.setProductId(1);
        rate.setStartDate(new Date());
        rate.setEndDate(new Date());
        rate.setPrice(1.0f);
        rate.setCurrencyCode("USD");

        RateEntity rateEntity = RateModelToEntity.map(rate);

        assert rateEntity.getId() != null;
        assertEquals(rate.getId(), rateEntity.getId().getValue());
        assertEquals(rate.getBrandId(), rateEntity.getBrandId().getValue());
        assertEquals(rate.getProductId(), rateEntity.getProductId().getValue());
        assertEquals(rate.getStartDate(), rateEntity.getStartDate().getValue());
        assertEquals(rate.getEndDate(), rateEntity.getEndDate().getValue());
        assertEquals(rate.getPrice(), rateEntity.getPrice().getValue());
        assertEquals(rate.getCurrencyCode(), rateEntity.getCurrencyCode().getValue());
    }
}
