package org.rates.infrastructure.persistence.rates.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.rates.domain.rates.*;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.rates.infrastructure.persistence.rates.repositories.RateRepository;

import java.util.Date;
import java.util.Optional;

import static junit.framework.Assert.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RatePersistenceFindTest {

    @Mock
    private RateRepository rateRepository;

    @InjectMocks
    private RatePersistenceImp ratePersistence;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindRate() {
        Date date   = new Date();
        Rate rate   = Rate.builder()
            .id(1L)
            .brandId(1)
            .productId(1)
            .startDate(date)
            .endDate(date)
            .price(1.0F)
            .currencyCode("USD")
            .build();

        when(this.rateRepository.findById(1L)).thenReturn(Optional.ofNullable(rate));

        RateEntity rateEntity = this.ratePersistence.find(1L);

        verify(this.rateRepository).findById(1L);

        assertNotNull(rateEntity.getBrandId().getValue());

        assert(rateEntity.getBrandId().getValue().equals(1));
        assert(rateEntity.getProductId().getValue().equals(1));
        assert(rateEntity.getStartDate().getValue().equals(date));
        assert(rateEntity.getEndDate().getValue().equals(date));
        assert(rateEntity.getPrice().getValue().equals(1.0F));
        assert(rateEntity.getCurrencyCode().getValue().equals("USD"));
    }

}
