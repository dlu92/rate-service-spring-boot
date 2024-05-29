package org.rates.domain.rates;

import java.util.Date;

public interface RatePersistence {

    RateEntity find(Long id);
    RateEntity save(RateEntity rateEntity);
    RateEntity update(Long id, RateEntity rateEntity);
    void delete(Long id);
    RateEntity[] searchByProductFinderDate(Integer productId, Integer brandId, Date date);

}
