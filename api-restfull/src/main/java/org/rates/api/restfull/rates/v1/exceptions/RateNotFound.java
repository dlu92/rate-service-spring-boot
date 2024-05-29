package org.rates.api.restfull.rates.v1.exceptions;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public final class RateNotFound extends RuntimeException {

    public RateNotFound() {
        super("Rate not found");
    }

}
