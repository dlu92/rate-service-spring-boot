package org.rates.application.rates.search;

import org.rates.domain.rates.RateEntity;

import java.util.Date;

public interface RateProductPriceFinderByDate {

    RateEntity[] search(Integer productId, Integer brandId, Date date);

}
