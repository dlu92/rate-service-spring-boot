package org.rates.infrastructure.persistence.rates.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_rates")
public final class Rate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer brandId;
    private Integer productId;
    private Date startDate;
    private Date endDate;
    private Float price;
    private String currencyCode;

}
