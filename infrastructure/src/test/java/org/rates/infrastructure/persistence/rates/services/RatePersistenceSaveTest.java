package org.rates.infrastructure.persistence.rates.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.rates.infrastructure.persistence.rates.repositories.RateRepository;
import java.util.Date;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceSaveTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RatePersistenceImp ratePersistence;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSaveRate() {
        Date date       = new Date();
        Rate rate       = Rate.builder()
            .brandId(1)
            .productId(1)
            .startDate(date)
            .endDate(date)
            .price(1.0F)
            .currencyCode("USD")
            .build();

        Rate rateReturn = Rate.builder()
            .id(1L)
            .brandId(1)
            .productId(1)
            .startDate(date)
            .endDate(date)
            .price(1.0F)
            .currencyCode("USD")
            .build();

        when(this.rateRepository.save(rate)).thenReturn(rateReturn);

        RateEntity rateEntity = new RateEntity(
            null,
            new RateBrandId(1),
            new RateProductId(1),
            new RateStartDate(date),
            new RateEndDate(date),
            new RatePrice(1.0F),
            new RateCurrencyCode("USD"),
            null,
            null
        );

        RateEntity rateEntitySaved = this.ratePersistence.save(rateEntity);

        verify(this.rateRepository).save(rate);

        assertNotNull(rateEntitySaved.getBrandId().getValue());

        assert(rateEntitySaved.getBrandId().getValue().equals(1));
        assert(rateEntitySaved.getProductId().getValue().equals(1));
        assert(rateEntitySaved.getStartDate().getValue().equals(date));
        assert(rateEntitySaved.getEndDate().getValue().equals(date));
        assert(rateEntitySaved.getPrice().getValue().equals(1.0F));
        assert(rateEntitySaved.getCurrencyCode().getValue().equals("USD"));
    }

}
