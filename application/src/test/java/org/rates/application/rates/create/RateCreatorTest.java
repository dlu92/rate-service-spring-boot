package org.rates.application.rates.create;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.currencies.*;
import org.rates.domain.rates.*;

import java.util.Date;
import java.util.Objects;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RateCreatorTest {
    @Mock
    private CurrencyIntegration currencyIntegrations;

    @Mock
    private RatePersistence ratePersistence;

    @InjectMocks
    private RateCreatorImp rateCreator;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateRate() {
        RateEntity rate = new RateEntity(
                null,
                new RateBrandId(1),
                new RateProductId(1),
                new RateStartDate(new Date()),
                new RateEndDate(new Date()),
                new RatePrice(1.0F),
                new RateCurrencyCode("USD"),
                null,
                null
        );

        when(this.ratePersistence.save(rate)).thenReturn(new RateEntity(
                new RateId(1L),
                new RateBrandId(1),
                new RateProductId(1),
                new RateStartDate(new Date()),
                new RateEndDate(new Date()),
                new RatePrice(1.0F),
                new RateCurrencyCode("USD"),
                null,
                null
        ));

        when(this.currencyIntegrations.getCurrencyByCode("usd")).thenReturn(new CurrencyEntity(
                new CurrencyCode("USD"),
                new CurrencySymbol("$"),
                new CurrencyDecimals(2)
        ));

        RateEntity finalRate = this.rateCreator.create(rate);

        assert(Objects.requireNonNull(finalRate.getCurrencySymbol()).getValue().equals("$"));
        assert(Objects.requireNonNull(finalRate.getCurrencyDecimals()).getValue().equals(2));

        verify(this.ratePersistence).save(rate);
        verify(this.currencyIntegrations).getCurrencyByCode("usd");
    }
}
