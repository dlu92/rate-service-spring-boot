package org.rates.api.restfull.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@AllArgsConstructor
public final class CustomErrorResponse {
    private Integer code;
    private HttpStatus status;
    private String message;
}
