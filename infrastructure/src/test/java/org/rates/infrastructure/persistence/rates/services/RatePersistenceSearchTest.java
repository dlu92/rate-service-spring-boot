package org.rates.infrastructure.persistence.rates.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.rates.RateEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.rates.infrastructure.persistence.rates.repositories.RateRepository;
import java.util.Date;
import java.util.Objects;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceSearchTest {

    private static final Long RATE_ID = 1L;
    private static final Integer BRAND_ID = 1;
    private static final Integer PRODUCT_ID = 1;
    private static final Date START_DATE = new Date();
    private static final Date END_DATE = new Date();
    private static final Float PRICE = 1.0F;
    private static final String CURRENCY_CODE = "USD";

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RatePersistenceImp ratePersistence;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testSearchRate() {

        Rate[] rates    = new Rate[]{
            Rate.builder()
                .id(RATE_ID)
                .brandId(BRAND_ID)
                .productId(PRODUCT_ID)
                .startDate(START_DATE)
                .endDate(END_DATE)
                .price(PRICE)
                .currencyCode(CURRENCY_CODE)
                .build()
        };

        when(this.rateRepository.productPriceFinderByDate(PRODUCT_ID, BRAND_ID, START_DATE))
            .thenReturn(rates);

        RateEntity[] rateEntities = this.ratePersistence.searchByProductFinderDate(PRODUCT_ID, BRAND_ID, START_DATE);

        for(RateEntity rateEntity : rateEntities) {

            assert(Objects.requireNonNull(rateEntity.getId()).getValue().equals(RATE_ID));
            assert(rateEntity.getBrandId().getValue().equals(BRAND_ID));
            assert(rateEntity.getProductId().getValue().equals(PRODUCT_ID));
            assert(rateEntity.getStartDate().getValue().equals(START_DATE));
            assert(rateEntity.getEndDate().getValue().equals(END_DATE));
            assert(rateEntity.getPrice().getValue().equals(PRICE));
            assert(rateEntity.getCurrencyCode().getValue().equals(CURRENCY_CODE));

            verify(this.rateRepository).productPriceFinderByDate(PRODUCT_ID, BRAND_ID, START_DATE);

        }

    }

    @Test
    public void testSearchRateNotResults() {

        when(this.rateRepository.productPriceFinderByDate(PRODUCT_ID, BRAND_ID, START_DATE))
            .thenReturn(new Rate[0]);

        RateEntity[] rateEntities = this.ratePersistence.searchByProductFinderDate(PRODUCT_ID, BRAND_ID, START_DATE);

        assert(rateEntities.length == 0);

        verify(this.rateRepository).productPriceFinderByDate(PRODUCT_ID, BRAND_ID, START_DATE);

    }

}
