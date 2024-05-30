package org.rates.infrastructure.integrations.currencies.mappers;

import org.junit.jupiter.api.Test;
import org.rates.domain.currencies.CurrencyEntity;
import org.rates.infrastructure.integrations.currencies.models.Currency;

public class CurrencyModelToEntityTest {

    private static final String CODE = "USD";
    private static final String SYMBOL = "$";
    private static final Integer DECIMALS = 2;

    @Test
    public void testMap() {

        Currency currency = Currency.builder()
            .code(CODE)
            .symbol(SYMBOL)
            .decimals(DECIMALS)
            .build();

        CurrencyEntity currencyEntity = CurrencyModelToEntity.map(currency);

        assert(currencyEntity.getCode().getValue().equals(currency.getCode()));
        assert(currencyEntity.getSymbol().getValue().equals(currency.getSymbol()));
        assert(currencyEntity.getDecimals().getValue().equals(currency.getDecimals()));

    }
}
