package org.rates.infrastructure.integrations.currencies.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public final class Currency {

    @JsonProperty("code")
    private String code;

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("decimals")
    private Integer decimals;

}
