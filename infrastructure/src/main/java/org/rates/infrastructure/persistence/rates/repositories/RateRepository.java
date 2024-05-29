package org.rates.infrastructure.persistence.rates.repositories;

import org.rates.infrastructure.persistence.rates.models.Rate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Date;

@Repository
public interface RateRepository extends JpaRepository<Rate, Long> {

    @Query("SELECT r FROM Rate r WHERE r.productId = :productId AND r.brandId = :brandId AND :date BETWEEN r.startDate AND r.endDate")
    Rate[] productPriceFinderByDate(@Param("productId") Integer productId, @Param("brandId") Integer brandId, @Param("date") Date date);

}
