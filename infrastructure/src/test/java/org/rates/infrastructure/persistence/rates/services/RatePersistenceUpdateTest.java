package org.rates.infrastructure.persistence.rates.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.exceptions.RateEntityNotFound;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.rates.infrastructure.persistence.rates.repositories.RateRepository;

import java.util.Date;
import java.util.Objects;
import java.util.Optional;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceUpdateTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";

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

        Rate rate               = Rate.builder()
            .id(RATE_ID)
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .price(PRICE)
            .currencyCode(CURRENCY_CODE)
            .build();

        Rate rateReturn         = Rate.builder()
                .id(RATE_ID)
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .price(PRICE)
                .currencyCode(CURRENCY_CODE)
                .build();

        RateEntity rateEntity   = new RateEntity(
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

        when(this.rateRepository.save(rate)).thenReturn(rateReturn);

        when(this.rateRepository.findById(RATE_ID)).thenReturn(Optional.ofNullable(rateReturn));

        RateEntity finalRateEntity = this.ratePersistence.update(RATE_ID, rateEntity);

        assert(Objects.requireNonNull(finalRateEntity.getId()).getValue().equals(RATE_ID));
        assert(finalRateEntity.getBrandId().getValue().equals(BRAND_ID));
        assert(finalRateEntity.getProductId().getValue().equals(PRODUCT_ID));
        assert(finalRateEntity.getStartDate().getValue().equals(START_DATE));
        assert(finalRateEntity.getEndDate().getValue().equals(END_DATE));
        assert(finalRateEntity.getPrice().getValue().equals(PRICE));
        assert(finalRateEntity.getCurrencyCode().getValue().equals(CURRENCY_CODE));

        verify(this.rateRepository).findById(RATE_ID);
        verify(this.rateRepository).save(rate);

    }

    @Test
    public void testUpdateRateNotFound() {

        RateEntity rateEntity   = new RateEntity(
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

        when(this.rateRepository.findById(RATE_ID)).thenThrow(new RateEntityNotFound());

        try {
            this.ratePersistence.update(RATE_ID, rateEntity);
        } catch (RateEntityNotFound e) {
            assert(true);
        }

        verify(this.rateRepository).findById(RATE_ID);

    }

}
