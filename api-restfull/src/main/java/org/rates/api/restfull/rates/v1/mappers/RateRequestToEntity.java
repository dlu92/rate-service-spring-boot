package org.rates.api.restfull.rates.v1.mappers;

import org.rates.api.restfull.rates.v1.exceptions.BadRequest;
import org.rates.api.restfull.rates.v1.requests.RateRequest;
import org.rates.domain.rates.*;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;

@Component
public final class RateRequestToEntity {

    public static RateEntity map(RateRequest rateRequest) {

        try {
            return new RateEntity(
                null,
                new RateBrandId(rateRequest.getBrandId()),
                new RateProductId(rateRequest.getProductId()),
                new RateStartDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rateRequest.getStartDate())),
                new RateEndDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).parse(rateRequest.getEndDate())),
                new RatePrice(rateRequest.getPrice()),
                new RateCurrencyCode(rateRequest.getCurrencyCode()),
                null,
                null
            );
        } catch (Exception e) {
            throw new BadRequest(e.getMessage());
        }

    }
}
