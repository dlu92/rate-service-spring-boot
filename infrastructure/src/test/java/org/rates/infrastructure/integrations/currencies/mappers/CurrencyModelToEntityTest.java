package org.rates.infrastructure.integrations.currencies.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.currencies.CurrencyEntity;
import org.rates.infrastructure.integrations.currencies.models.Currency;

import static junit.framework.Assert.assertEquals;

public class CurrencyModelToEntityTest {

    @Test
    public void testMap() {
        Currency currency = Currency.builder()
            .code("USD")
            .symbol("$")
            .decimals(2)
            .build();

        CurrencyEntity currencyEntity = CurrencyModelToEntity.map(currency);

        assertEquals(currencyEntity.getCode().getValue(), currency.getCode());
        assertEquals(currencyEntity.getSymbol().getValue(), currency.getSymbol());
        assertEquals(currencyEntity.getDecimals().getValue(), currency.getDecimals());
    }
}
