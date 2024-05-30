package org.rates.application.rates.update;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.currencies.*;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.exceptions.RateEntityNotFound;

import java.util.Date;
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public final class RateUpdatedTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";
    private static final String CURRENCY_SYMBOL = "$";
    private static final Integer CURRENCY_DECIMALS = 2;

    @Mock
    private CurrencyIntegration currencyIntegrations;

    @Mock
    private RatePersistence ratePersistence;

    @InjectMocks
    private RateUpdatedImp rateUpdated;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testUpdateRate() {

        RateEntity rate     = new RateEntity(
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

        when(this.ratePersistence.update(RATE_ID, rate))
            .thenReturn(new RateEntity(
                new RateId(RATE_ID),
                new RateBrandId(BRAND_ID),
                new RateProductId(PRODUCT_ID),
                new RateStartDate(START_DATE),
                new RateEndDate(END_DATE),
                new RatePrice(PRICE),
                new RateCurrencyCode(CURRENCY_CODE),
                null,
                null
            ));

        when(this.currencyIntegrations.getCurrencyByCode(CURRENCY_CODE.toLowerCase()))
            .thenReturn(new CurrencyEntity(
                new CurrencyCode(CURRENCY_CODE),
                new CurrencySymbol(CURRENCY_SYMBOL),
                new CurrencyDecimals(CURRENCY_DECIMALS)
            ));

        RateEntity finalRate = this.rateUpdated.update(RATE_ID, rate);

        verify(this.ratePersistence).update(RATE_ID, rate);
        verify(this.currencyIntegrations).getCurrencyByCode(CURRENCY_CODE.toLowerCase());

        assert(Objects.requireNonNull(finalRate.getId()).getValue().equals(RATE_ID));
        assert(finalRate.getBrandId().getValue().equals(BRAND_ID));
        assert(finalRate.getProductId().getValue().equals(PRODUCT_ID));
        assert(finalRate.getStartDate().getValue().equals(START_DATE));
        assert(finalRate.getEndDate().getValue().equals(END_DATE));
        assert(finalRate.getPrice().getValue().equals(PRICE));
        assert(finalRate.getCurrencyCode().getValue().equals(CURRENCY_CODE));
        assert(Objects.requireNonNull(finalRate.getCurrencySymbol()).getValue().equals(CURRENCY_SYMBOL));
        assert(Objects.requireNonNull(finalRate.getCurrencyDecimals()).getValue().equals(CURRENCY_DECIMALS));

    }

    @Test
    public void testUpdateRateException() {

        Long rateId = 2L;
        RateEntity rate     = new RateEntity(
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

        when(this.ratePersistence.update(rateId, rate)).thenThrow(new RateEntityNotFound());

        try {
            this.rateUpdated.update(rateId, rate);
        } catch (RateEntityNotFound e) {
            assert(true);
        }

        verify(this.ratePersistence).update(rateId, rate);

    }

}
