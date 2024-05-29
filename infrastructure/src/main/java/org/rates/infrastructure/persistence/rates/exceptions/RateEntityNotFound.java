package org.rates.infrastructure.persistence.rates.exceptions;

import lombok.experimental.StandardException;
import org.rates.infrastructure.persistence.exceptions.EntityNotFound;

@StandardException
public final class RateEntityNotFound extends EntityNotFound {

    public RateEntityNotFound() {
        super("Rate not found");
    }

}
