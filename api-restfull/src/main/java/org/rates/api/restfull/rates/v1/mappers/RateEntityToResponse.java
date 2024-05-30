package org.rates.api.restfull.rates.v1.mappers;

import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.domain.rates.RateEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;

@Component
public final class RateEntityToResponse {

    public static RateResponse map(RateEntity rateEntity) {

        BigDecimal price = BigDecimal.valueOf(rateEntity.getPrice().getValue());
        assert rateEntity.getCurrencyDecimals() != null;
        price = price.setScale(rateEntity.getCurrencyDecimals().getValue(), RoundingMode.HALF_UP);

        assert rateEntity.getId() != null;
        assert rateEntity.getCurrencySymbol() != null;
        return new RateResponse(
            rateEntity.getId().getValue(),
            rateEntity.getBrandId().getValue(),
            rateEntity.getProductId().getValue(),
            (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(rateEntity.getStartDate().getValue()),
            (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(rateEntity.getEndDate().getValue()),
            price,
            rateEntity.getCurrencyCode().getValue(),
            rateEntity.getCurrencySymbol().getValue()
        );
    }

}
