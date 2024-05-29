package org.rates.application.rates.detele;

import lombok.AllArgsConstructor;
import org.rates.domain.rates.RatePersistence;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public final class RateDeletedImp implements RateDeleted {

    private final RatePersistence ratePersistence;

    public void delete(Long id) {
        this.ratePersistence.delete(id);
    }

}
