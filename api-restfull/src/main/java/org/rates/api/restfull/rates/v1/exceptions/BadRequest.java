package org.rates.api.restfull.rates.v1.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public final class BadRequest extends RuntimeException {

    public BadRequest(String message) {
        super("Bad Request: " + message);
    }

}
