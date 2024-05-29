package org.rates.domain.currencies;

public interface CurrencyIntegration {

    CurrencyEntity[] getCurrencies();
    CurrencyEntity getCurrencyByCode(String code);

}