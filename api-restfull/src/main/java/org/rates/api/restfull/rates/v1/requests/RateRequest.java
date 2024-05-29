package org.rates.api.restfull.rates.v1.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public final class RateRequest {

    @NotNull(message = "Brand id must not be null")
    @Min(value = 1, message = "Brand id must be greater than 0")
    @JsonProperty("brand_id")
    private Integer brandId;

    @NotNull(message = "Product id must not be null")
    @Min(value = 1, message = "Product id must be greater than 0")
    @JsonProperty("product_id")
    private Integer productId;

    @NotNull(message = "Start date must not be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Start date must be in the format yyyy-MM-dd HH:mm:ss")
    @JsonProperty("start_date")
    private String startDate;

    @NotNull(message = "End date must not be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "End date must be in the format yyyy-MM-dd HH:mm:ss")
    @JsonProperty("end_date")
    private String endDate;

    @NotNull(message = "Price must not be null")
    @Min(value = 0, message = "Price must be greater than or equal to 0")
    @JsonProperty("price")
    private Float price;

    @NotNull(message = "Currency code must not be null")
    @Pattern(regexp = "EUR|USD", message = "Currency must be either EUR or USD")
    @JsonProperty("currency_code")
    private String currencyCode;

}
