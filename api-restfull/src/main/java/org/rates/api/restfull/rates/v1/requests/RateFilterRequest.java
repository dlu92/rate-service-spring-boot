package org.rates.api.restfull.rates.v1.requests;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public final class RateFilterRequest {

    @NotNull(message = "Brand id must not be null")
    @Min(value = 1, message = "Brand id must be greater than 0")
    private Integer brandId;

    @NotNull(message = "Product id must not be null")
    @Min(value = 1, message = "Product id must be greater than 0")
    private Integer productId;

    @NotNull(message = "Date must not be null")
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}", message = "Date must be in the format yyyy-MM-dd HH:mm:ss")
    private String date;

    public RateFilterRequest(Integer brand_id, Integer product_id, String date) {
        this.brandId = brand_id;
        this.productId = product_id;
        this.date = date;
    }

}
