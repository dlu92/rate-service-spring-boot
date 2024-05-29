package org.rates.domain.rates;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.lang.Nullable;

@Data
@AllArgsConstructor
public final class RateEntity {

    private @Nullable RateId id;
    private RateBrandId brandId;
    private RateProductId productId;
    private RateStartDate startDate;
    private RateEndDate endDate;
    private RatePrice price;
    private RateCurrencyCode currencyCode;
    private @Nullable RateCurrencySymbol currencySymbol;
    private @Nullable RateCurrencyDecimals currencyDecimals;

}
