package org.rates.infrastructure.integrations.currencies.mappers;

import org.rates.domain.currencies.CurrencyCode;
import org.rates.domain.currencies.CurrencyDecimals;
import org.rates.domain.currencies.CurrencyEntity;
import org.rates.domain.currencies.CurrencySymbol;
import org.rates.infrastructure.integrations.currencies.models.Currency;
import org.springframework.stereotype.Component;

@Component
public final class CurrencyModelToEntity {

    public static CurrencyEntity map(Currency currency) {
        return new CurrencyEntity(
            new CurrencyCode(currency.getCode()),
            new CurrencySymbol(currency.getSymbol()),
            new CurrencyDecimals(currency.getDecimals())
        );
    }

}
