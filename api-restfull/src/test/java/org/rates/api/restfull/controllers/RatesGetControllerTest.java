package org.rates.api.restfull.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.api.restfull.rates.v1.controllers.RatePatchController;
import org.rates.api.restfull.rates.v1.controllers.RatesGetController;
import org.rates.api.restfull.rates.v1.requests.RateFilterRequest;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.search.RateProductPriceFinderByDate;
import org.rates.application.rates.update.RateUpdated;
import org.rates.domain.rates.*;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatesGetControllerTest {

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
    private RateProductPriceFinderByDate rateSearch;

    @InjectMocks
    private RatesGetController ratesGetController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testGetRate() throws ParseException {

        RateEntity[] rates   = new RateEntity[] {
            new RateEntity(
                new RateId(RATE_ID),
                new RateBrandId(BRAND_ID),
                new RateProductId(PRODUCT_ID),
                new RateStartDate(START_DATE),
                new RateEndDate(END_DATE),
                new RatePrice(PRICE),
                new RateCurrencyCode(CURRENCY_CODE),
                new RateCurrencySymbol(CURRENCY_SYMBOL),
                new RateCurrencyDecimals(CURRENCY_DECIMALS)
            )
        };

        RateFilterRequest request = new RateFilterRequest(
            PRODUCT_ID,
            BRAND_ID,
            (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(START_DATE)
        );

        when(this.rateSearch.search(any(Integer.class), any(Integer.class), any(Date.class))).thenReturn(rates);

        ResponseEntity<RateResponse[]> response = this.ratesGetController.getList(request);

        assert(Objects.requireNonNull(response.getBody()).length == 1);

        for (RateResponse rateResponse : Objects.requireNonNull(response.getBody())) {

            assert(Objects.requireNonNull(rateResponse.getId()).equals(RATE_ID));
            assert(rateResponse.getBrand_id().equals(BRAND_ID));
            assert(rateResponse.getProduct_id().equals(PRODUCT_ID));
            assert(rateResponse.getStart_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(START_DATE)));
            assert(rateResponse.getEnd_date().equals((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(END_DATE)));
            assert(rateResponse.getPrice().equals(BigDecimal.valueOf(PRICE).setScale(CURRENCY_DECIMALS, RoundingMode.HALF_UP)));
            assert(rateResponse.getCurrency_code().equals(CURRENCY_CODE));
            assert(rateResponse.getCurrency_symbol().equals(CURRENCY_SYMBOL));

            verify(this.rateSearch).search(any(Integer.class), any(Integer.class), any(Date.class));

        }

    }

}
