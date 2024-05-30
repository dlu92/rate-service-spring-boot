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

        Date date           = new Date();
        RateEntity rate     = new RateEntity(
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

        when(this.ratePersistence.update(1L, rate)).thenReturn(new RateEntity(
            new RateId(1L),
            new RateBrandId(1),
            new RateProductId(1),
            new RateStartDate(date),
            new RateEndDate(date),
            new RatePrice(1.0F),
            new RateCurrencyCode("USD"),
            null,
            null
        ));

        when(this.ratePersistence.update(2L, rate)).thenThrow(new RateEntityNotFound());

        when(this.currencyIntegrations.getCurrencyByCode("usd")).thenReturn(new CurrencyEntity(
                new CurrencyCode("USD"),
                new CurrencySymbol("$"),
                new CurrencyDecimals(2)
        ));

        RateEntity finalRate = this.rateUpdated.update(1L, rate);

        verify(this.ratePersistence).update(1L, rate);
        verify(this.currencyIntegrations).getCurrencyByCode("usd");

        assert(Objects.requireNonNull(finalRate.getId()).getValue().equals(1L));
        assert(finalRate.getBrandId().getValue().equals(1));
        assert(finalRate.getProductId().getValue().equals(1));
        assert(finalRate.getStartDate().getValue().equals(date));
        assert(finalRate.getEndDate().getValue().equals(date));
        assert(finalRate.getPrice().getValue().equals(1.0F));
        assert(finalRate.getCurrencyCode().getValue().equals("USD"));
        assert(Objects.requireNonNull(finalRate.getCurrencySymbol()).getValue().equals("$"));
        assert(Objects.requireNonNull(finalRate.getCurrencyDecimals()).getValue().equals(2));

        try {
            this.rateUpdated.update(2L, rate);
        } catch (RateEntityNotFound e) {
            assert(true);
        }

    }

}
