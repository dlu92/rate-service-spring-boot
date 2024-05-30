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

public final class RateCreatorTest {
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

        Date date       = new Date();
        RateEntity rate = new RateEntity(
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

        when(this.ratePersistence.save(rate)).thenReturn(new RateEntity(
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

        when(this.currencyIntegrations.getCurrencyByCode("usd")).thenReturn(new CurrencyEntity(
                new CurrencyCode("USD"),
                new CurrencySymbol("$"),
                new CurrencyDecimals(2)
        ));

        RateEntity finalRate = this.rateCreator.create(rate);

        verify(this.ratePersistence).save(rate);
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

    }
}
