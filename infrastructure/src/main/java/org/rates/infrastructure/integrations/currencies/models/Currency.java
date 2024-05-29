package org.rates.infrastructure.integrations.currencies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public final class Currency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("decimals")
    private Integer decimals;

}
