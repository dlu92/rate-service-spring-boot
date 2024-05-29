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

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceSearchTest {

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
        Date date       = new Date();
        Rate[] rates    = new Rate[]{
            Rate.builder()
                .id(1L)
                .brandId(1)
                .productId(1)
                .startDate(date)
                .endDate(date)
                .price(1.0F)
                .currencyCode("USD")
                .build()
        };

        when(this.rateRepository.productPriceFinderByDate(1, 1, date))
            .thenReturn(rates);

        RateEntity[] rateEntities = this.ratePersistence.searchByProductFinderDate(1, 1, date);

        verify(this.rateRepository).productPriceFinderByDate(1, 1, date);

        for(RateEntity rateEntity : rateEntities) {
            assertNotNull(rateEntity.getBrandId().getValue());

            assert(rateEntity.getBrandId().getValue().equals(1));
            assert(rateEntity.getProductId().getValue().equals(1));
            assert(rateEntity.getStartDate().getValue().equals(date));
            assert(rateEntity.getEndDate().getValue().equals(date));
            assert(rateEntity.getPrice().getValue().equals(1.0F));
            assert(rateEntity.getCurrencyCode().getValue().equals("USD"));
        }
    }

}
