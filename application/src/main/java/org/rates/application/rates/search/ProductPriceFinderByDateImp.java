package org.rates.application.rates.search;

import lombok.AllArgsConstructor;
import org.rates.domain.currencies.CurrencyEntity;
import org.rates.domain.currencies.CurrencyIntegration;
import org.rates.domain.rates.RateCurrencyDecimals;
import org.rates.domain.rates.RateCurrencySymbol;
import org.rates.domain.rates.RateEntity;
import org.rates.domain.rates.RatePersistence;
import org.springframework.stereotype.Service;
import java.util.Date;

@Service
@AllArgsConstructor
public final class ProductPriceFinderByDateImp implements ProductPriceFinderByDate {

    private final RatePersistence ratePersistence;
    private final CurrencyIntegration currencyIntegrations;

    public RateEntity[] search(Integer productId, Integer brandId, Date date) {
        RateEntity[] rates = this.ratePersistence.searchByProductFinderDate(productId, brandId, date);

        for (RateEntity rate : rates) {
            CurrencyEntity currency = this.currencyIntegrations.getCurrencyByCode(rate.getCurrencyCode().getValue().toLowerCase());

            rate.setCurrencySymbol(new RateCurrencySymbol(currency.getSymbol().getValue()));
            rate.setCurrencyDecimals(new RateCurrencyDecimals(currency.getDecimals().getValue()));
        }

        return rates;
    }
}
