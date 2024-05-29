package org.rates.domain.currencies;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public final class CurrencyEntity {

    private CurrencyCode code;
    private CurrencySymbol symbol;
    private CurrencyDecimals decimals;

}
