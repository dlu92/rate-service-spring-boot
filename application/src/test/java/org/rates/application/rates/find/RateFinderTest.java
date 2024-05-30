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

        Date date = new Date();

        when(this.ratePersistence.find(1L)).thenReturn(new RateEntity(
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

        when(this.ratePersistence.find(2L)).thenThrow(new RateEntityNotFound());

        when(this.currencyIntegrations.getCurrencyByCode("usd")).thenReturn(new CurrencyEntity(
                new CurrencyCode("USD"),
                new CurrencySymbol("$"),
                new CurrencyDecimals(2)
        ));

        RateEntity rate = this.rateFinder.find(1L);

        verify(this.ratePersistence).find(1L);
        verify(this.currencyIntegrations).getCurrencyByCode("usd");

        assert(Objects.requireNonNull(rate.getId()).getValue().equals(1L));
        assert(rate.getBrandId().getValue().equals(1));
        assert(rate.getProductId().getValue().equals(1));
        assert(rate.getStartDate().getValue().equals(date));
        assert(rate.getEndDate().getValue().equals(date));
        assert(rate.getPrice().getValue().equals(1.0F));
        assert(rate.getCurrencyCode().getValue().equals("USD"));
        assert(Objects.requireNonNull(rate.getCurrencySymbol()).getValue().equals("$"));
        assert(Objects.requireNonNull(rate.getCurrencyDecimals()).getValue().equals(2));

        try {
            this.rateFinder.find(2L);
        } catch (RateEntityNotFound e) {
            assert(true);
        }

    }

}
