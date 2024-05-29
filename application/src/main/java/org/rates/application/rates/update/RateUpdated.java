package org.rates.application.rates.update;

import org.rates.domain.rates.RateEntity;

public interface RateUpdated {
    RateEntity update(Long id, RateEntity rateEntity);
}
