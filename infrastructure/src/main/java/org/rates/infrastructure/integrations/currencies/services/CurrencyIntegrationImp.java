package org.rates.infrastructure.integrations.currencies.services;

import org.rates.domain.currencies.CurrencyEntity;
import org.rates.domain.currencies.CurrencyIntegration;
import org.rates.infrastructure.integrations.connections.JsonApiConnection;
import org.rates.infrastructure.integrations.currencies.mappers.CurrencyModelCollectionToEntity;
import org.rates.infrastructure.integrations.currencies.mappers.CurrencyModelToEntity;
import org.rates.infrastructure.integrations.currencies.models.Currency;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public final class CurrencyIntegrationImp implements CurrencyIntegration {

    private final JsonApiConnection connector;
    private final String host;

    CurrencyIntegrationImp(JsonApiConnection jsonApiConnection) {
        this.connector  = jsonApiConnection;
        this.host       = "http://localhost:8181/v1";
    }

    public CurrencyEntity[] getCurrencies() {
        Currency[] currency = this.connector.send(this.host + "/currencies", null, HttpMethod.GET, Currency[].class);

        return CurrencyModelCollectionToEntity.map(currency);
    }

    public CurrencyEntity getCurrencyByCode(String code) {
        Currency currency = this.connector.send(this.host + "/currencies/" + code, null, HttpMethod.GET, Currency.class);

        return CurrencyModelToEntity.map(currency);
    }

}
