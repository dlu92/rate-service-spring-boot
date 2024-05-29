package org.rates.application.rates.update;

import lombok.AllArgsConstructor;
import org.rates.domain.currencies.CurrencyEntity;
import org.rates.domain.currencies.CurrencyIntegration;
import org.rates.domain.rates.RateCurrencyDecimals;
import org.rates.domain.rates.RateCurrencySymbol;
import org.rates.domain.rates.RateEntity;
import org.rates.domain.rates.RatePersistence;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class RateUpdatedImp implements RateUpdated {

    private final RatePersistence ratePersistence;
    private final CurrencyIntegration currencyIntegrations;

    public RateEntity update(Long id, RateEntity rateEntity) {
        RateEntity rate         = this.ratePersistence.update(id, rateEntity);
        CurrencyEntity currency = this.currencyIntegrations.getCurrencyByCode(rate.getCurrencyCode().getValue().toLowerCase());

        rate.setCurrencySymbol(new RateCurrencySymbol(currency.getSymbol().getValue()));
        rate.setCurrencyDecimals(new RateCurrencyDecimals(currency.getDecimals().getValue()));

        return rate;
    }

}
