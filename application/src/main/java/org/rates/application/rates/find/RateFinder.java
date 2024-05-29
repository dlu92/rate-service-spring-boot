package org.rates.application.rates.find;

import org.rates.domain.rates.RateEntity;

public interface RateFinder {
    RateEntity find(Long id);
}
