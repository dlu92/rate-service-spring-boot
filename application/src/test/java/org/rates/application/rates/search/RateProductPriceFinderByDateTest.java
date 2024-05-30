package org.rates.application.rates.search;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.currencies.*;
import org.rates.domain.rates.*;

import java.util.Date;
import java.util.Objects;

import static org.mockito.Mockito.when;

public final class RateProductPriceFinderByDateTest {

    @Mock
    private CurrencyIntegration currencyIntegrations;

    @Mock
    private RatePersistence ratePersistence;

    @InjectMocks
    private RateProductPriceFinderByDateImp rateSearch;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearch() {

        Date date           = new Date();
        RateEntity rate     = new RateEntity(
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

        when(this.ratePersistence.searchByProductFinderDate(1, 1, date))
            .thenReturn(new RateEntity[] {
                rate
            });

        when(this.ratePersistence.searchByProductFinderDate(2, 1, date))
            .thenReturn(new RateEntity[0]);

        when(this.currencyIntegrations.getCurrencyByCode("usd")).thenReturn(new CurrencyEntity(
                new CurrencyCode("USD"),
                new CurrencySymbol("$"),
                new CurrencyDecimals(2)
        ));

        RateEntity[] rates = this.rateSearch.search(1, 1, date);

        assert(rates.length == 1);

        for (RateEntity finalRate : rates) {

            assert(Objects.requireNonNull(finalRate.getId()).getValue().equals(1L));
            assert(finalRate.getBrandId().getValue().equals(1));
            assert(finalRate.getProductId().getValue().equals(1));
            assert(finalRate.getStartDate().getValue().equals(date));
            assert(finalRate.getEndDate().getValue().equals(date));
            assert(finalRate.getPrice().getValue().equals(1.0F));
            assert(finalRate.getCurrencyCode().getValue().equals("USD"));
            assert(Objects.requireNonNull(finalRate.getCurrencySymbol()).getValue().equals("$"));
            assert(Objects.requireNonNull(finalRate.getCurrencyDecimals()).getValue().equals(2));

        }

        RateEntity[] emptyRates = this.rateSearch.search(2, 1, date);

        assert(emptyRates.length == 0);

    }

}
