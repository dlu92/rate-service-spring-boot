package org.rates.application.rates.find;

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

public final class RateFinderTest {

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
    private RateFinderImp rateFinder;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFind() {

        when(this.ratePersistence.find(RATE_ID))
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

        RateEntity rate = this.rateFinder.find(RATE_ID);

        verify(this.ratePersistence).find(RATE_ID);
        verify(this.currencyIntegrations).getCurrencyByCode(CURRENCY_CODE.toLowerCase());

        assert(Objects.requireNonNull(rate.getId()).getValue().equals(RATE_ID));
        assert(rate.getBrandId().getValue().equals(BRAND_ID));
        assert(rate.getProductId().getValue().equals(PRODUCT_ID));
        assert(rate.getStartDate().getValue().equals(START_DATE));
        assert(rate.getEndDate().getValue().equals(END_DATE));
        assert(rate.getPrice().getValue().equals(PRICE));
        assert(rate.getCurrencyCode().getValue().equals(CURRENCY_CODE));
        assert(Objects.requireNonNull(rate.getCurrencySymbol()).getValue().equals(CURRENCY_SYMBOL));
        assert(Objects.requireNonNull(rate.getCurrencyDecimals()).getValue().equals(CURRENCY_DECIMALS));

    }

    @Test
    public void testFindException() {

        Long rateId = 2L;

        when(this.ratePersistence.find(rateId)).thenThrow(new RateEntityNotFound());

        try {
            this.rateFinder.find(rateId);
        } catch (RateEntityNotFound e) {
            assert(true);
        }

        verify(this.ratePersistence).find(rateId);

    }

}
