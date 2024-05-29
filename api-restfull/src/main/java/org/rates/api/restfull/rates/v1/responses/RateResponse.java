package org.rates.api.restfull.rates.v1.responses;

import lombok.Getter;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;

@Getter
@ResponseBody
public final class RateResponse {

    private final Long id;
    private final Integer brand_id;
    private final Integer product_id;
    private final String start_date;
    private final String end_date;
    private final BigDecimal price;
    private final String currency_code;
    private final String currency_symbol;

    public RateResponse(
        Long id,
        Integer brandId,
        Integer productId,
        String startDate,
        String endDate,
        BigDecimal price,
        String currencyCode,
        String currencySymbol
    ) {
        this.id = id;
        this.brand_id = brandId;
        this.product_id = productId;
        this.start_date = startDate;
        this.end_date = endDate;
        this.price = price;
        this.currency_code = currencyCode;
        this.currency_symbol = currencySymbol;
    }
}
