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
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceSaveTest {

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
    public void testSaveRate() {

        Rate rate       = Rate.builder()
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .price(PRICE)
            .currencyCode(CURRENCY_CODE)
            .build();

        Rate rateReturn = Rate.builder()
            .id(RATE_ID)
            .brandId(BRAND_ID)
            .productId(PRODUCT_ID)
            .startDate(START_DATE)
            .endDate(END_DATE)
            .price(PRICE)
            .currencyCode(CURRENCY_CODE)
            .build();

        when(this.rateRepository.save(rate)).thenReturn(rateReturn);

        RateEntity rateEntity = new RateEntity(
            null,
            new RateBrandId(BRAND_ID),
            new RateProductId(PRODUCT_ID),
            new RateStartDate(START_DATE),
            new RateEndDate(END_DATE),
            new RatePrice(PRICE),
            new RateCurrencyCode(CURRENCY_CODE),
            null,
            null
        );

        RateEntity rateEntitySaved = this.ratePersistence.save(rateEntity);

        assert(Objects.requireNonNull(rateEntitySaved.getId()).getValue().equals(RATE_ID));
        assert(rateEntitySaved.getBrandId().getValue().equals(BRAND_ID));
        assert(rateEntitySaved.getProductId().getValue().equals(PRODUCT_ID));
        assert(rateEntitySaved.getStartDate().getValue().equals(START_DATE));
        assert(rateEntitySaved.getEndDate().getValue().equals(END_DATE));
        assert(rateEntitySaved.getPrice().getValue().equals(PRICE));
        assert(rateEntitySaved.getCurrencyCode().getValue().equals(CURRENCY_CODE));

        verify(this.rateRepository).save(rate);

    }

}
