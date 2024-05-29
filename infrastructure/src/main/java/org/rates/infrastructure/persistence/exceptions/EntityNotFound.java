package org.rates.infrastructure.persistence.exceptions;

import lombok.experimental.StandardException;

@StandardException
public abstract class EntityNotFound extends RuntimeException {

    public EntityNotFound(String message) {
        super(message);
    }
        
}
