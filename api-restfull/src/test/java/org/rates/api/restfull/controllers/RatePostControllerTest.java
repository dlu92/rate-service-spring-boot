package org.rates.api.restfull.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.api.restfull.rates.v1.controllers.RatePostController;
import org.rates.api.restfull.rates.v1.requests.RateRequest;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.create.RateCreator;
import org.rates.domain.rates.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePostControllerTest {

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
    private RateCreator rateCreator;

    @InjectMocks
    private RatePostController ratePostController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testPostRate() {

        RateEntity rate = new RateEntity(
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

        RateRequest rateRequest = new RateRequest();

        rateRequest.setBrandId(BRAND_ID);
        rateRequest.setProductId(PRODUCT_ID);
        rateRequest.setStartDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(START_DATE));
        rateRequest.setEndDate((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(END_DATE));
        rateRequest.setPrice(PRICE);
        rateRequest.setCurrencyCode(CURRENCY_CODE);

        when(rateCreator.create(any(RateEntity.class))).thenReturn(rate);

        ResponseEntity<RateResponse> response = ratePostController.create(rateRequest);

        assertNotNull(response.getBody());
        assert(Objects.requireNonNull(response.getBody()).getId().equals(RATE_ID));
        assert(response.getBody().getBrand_id().equals(BRAND_ID));
        assert(response.getBody().getProduct_id().equals(PRODUCT_ID));
        assert(response.getBody().getStart_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(START_DATE)));
        assert(response.getBody().getEnd_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(END_DATE)));
        assert(response.getBody().getPrice().equals(BigDecimal.valueOf(PRICE).setScale(CURRENCY_DECIMALS, RoundingMode.HALF_UP)));
        assert(response.getBody().getCurrency_code().equals(CURRENCY_CODE));
        assert(response.getBody().getCurrency_symbol().equals(CURRENCY_SYMBOL));

        verify(rateCreator).create(any(RateEntity.class));
    }

}