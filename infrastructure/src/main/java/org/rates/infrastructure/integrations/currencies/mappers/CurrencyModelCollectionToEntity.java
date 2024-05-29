package org.rates.infrastructure.integrations.currencies.mappers;

import org.rates.domain.currencies.CurrencyEntity;
import org.rates.infrastructure.integrations.currencies.models.Currency;
import org.springframework.stereotype.Component;
import java.util.Arrays;

@Component
public final class CurrencyModelCollectionToEntity {

    public static CurrencyEntity[] map(Currency[] currencies) {
        return Arrays.stream(currencies)
            .map(CurrencyModelToEntity::map)
            .toArray(CurrencyEntity[]::new);
    }

}
