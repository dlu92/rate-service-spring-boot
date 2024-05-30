package org.rates.api.restfull.controllers;

import junit.framework.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.api.restfull.rates.v1.controllers.RateGetController;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.find.RateFinder;
import org.rates.domain.rates.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RateGetControllerTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";
    private static final String CURRENCY_SYMBOL = "$";
    private static final Integer CURRENCY_DECIMALS = 2;

    @Mock
    private RateFinder rateFinder;

    @InjectMocks
    private RateGetController rateGetController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRate() {

        RateEntity rate     = new RateEntity(
            new RateId(RATE_ID),
            new RateBrandId(BRAND_ID),
            new RateProductId(PRODUCT_ID),
            new RateStartDate(START_DATE),
            new RateEndDate(END_DATE),
            new RatePrice(PRICE),
            new RateCurrencyCode(CURRENCY_CODE),
            new RateCurrencySymbol(CURRENCY_SYMBOL),
            new RateCurrencyDecimals(CURRENCY_DECIMALS)
        );

        when(this.rateFinder.find(any(Long.class))).thenReturn(rate);

        ResponseEntity<RateResponse> response = this.rateGetController.getById(RATE_ID);

        assert(Objects.requireNonNull(response.getBody()).getId().equals(RATE_ID));
        assert(response.getBody().getBrand_id().equals(BRAND_ID));
        assert(response.getBody().getProduct_id().equals(PRODUCT_ID));
        assert(response.getBody().getStart_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(START_DATE)));
        assert(response.getBody().getEnd_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(END_DATE)));
        assert(response.getBody().getPrice().equals(BigDecimal.valueOf(PRICE).setScale(CURRENCY_DECIMALS, RoundingMode.HALF_UP)));
        assert(response.getBody().getCurrency_code().equals(CURRENCY_CODE));
        assert(response.getBody().getCurrency_symbol().equals(CURRENCY_SYMBOL));

        verify(this.rateFinder).find(any(Long.class));

    }

}
