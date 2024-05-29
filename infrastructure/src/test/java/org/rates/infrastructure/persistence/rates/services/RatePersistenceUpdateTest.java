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
import java.util.Optional;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceUpdateTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RatePersistenceImp ratePersistence;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateRate() {
        Date date       = new Date();
        Rate rate       = Rate.builder()
            .id(1L)
            .brandId(1)
            .productId(1)
            .startDate(date)
            .endDate(date)
            .price(1.0F)
            .currencyCode("USD")
            .build();

        RateEntity rateEntity = new RateEntity(
            new RateId(1L),
            new RateBrandId(1),
            new RateProductId(1),
            new RateStartDate(date),
            new RateEndDate(date),
            new RatePrice(1.0F),
            new RateCurrencyCode("USD"),
            null,
            null
        );

        when(this.rateRepository.findById(1L)).thenReturn(Optional.ofNullable(rate));
        assert rate != null;
        when(this.rateRepository.save(rate)).thenReturn(rate);

        RateEntity finalRateEntity = this.ratePersistence.update(1L, rateEntity);

        verify(this.rateRepository).findById(1L);
        verify(this.rateRepository).save(rate);

        assertNotNull(finalRateEntity.getBrandId().getValue());

        assert(finalRateEntity.getBrandId().getValue().equals(rateEntity.getBrandId().getValue()));
        assert(finalRateEntity.getProductId().getValue().equals(rateEntity.getProductId().getValue()));
        assert(finalRateEntity.getStartDate().getValue().equals(rateEntity.getStartDate().getValue()));
        assert(finalRateEntity.getEndDate().getValue().equals(rateEntity.getEndDate().getValue()));
        assert(finalRateEntity.getPrice().getValue().equals(rateEntity.getPrice().getValue()));
        assert(finalRateEntity.getCurrencyCode().getValue().equals(rateEntity.getCurrencyCode().getValue()));
    }

}
