package org.rates.infrastructure.persistence.rates.services;

import lombok.AllArgsConstructor;
import org.rates.domain.rates.RateEntity;
import org.rates.domain.rates.RatePersistence;
import org.rates.infrastructure.persistence.rates.exceptions.RateEntityNotFound;
import org.rates.infrastructure.persistence.rates.mappers.RateEntityToModel;
import org.rates.infrastructure.persistence.rates.mappers.RateModelCollectionToEntity;
import org.rates.infrastructure.persistence.rates.mappers.RateModelToEntity;
import org.rates.infrastructure.persistence.rates.models.Rate;
import org.rates.infrastructure.persistence.rates.repositories.RateRepository;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
@AllArgsConstructor
public final class RatePersistenceImp implements RatePersistence {

    private final RateRepository rateRepository;

    public RateEntity find(Long id) {
        return RateModelToEntity.map(this.findById(id));
    }

    public RateEntity save(RateEntity rateEntity) {
        return RateModelToEntity.map(this.rateRepository.save(RateEntityToModel.map(rateEntity, new Rate())));
    }

    public RateEntity update(Long id, RateEntity rateEntity) {
        Rate rate = this.findById(id);

        return RateModelToEntity.map(this.rateRepository.save(RateEntityToModel.map(rateEntity, rate)));
    }

    public void delete(Long id) {
        this.findById(id);
        this.rateRepository.deleteById(id);
    }

    public RateEntity[] searchByProductFinderDate(Integer productId, Integer brandId, Date date) {
        return RateModelCollectionToEntity.map(this.rateRepository.productPriceFinderByDate(productId, brandId, date));
    }

    private Rate findById(Long id) {
        Optional<Rate> rate = this.rateRepository.findById(id);

        if(rate.isPresent()) {
            return rate.get();
        } else {
            throw new RateEntityNotFound();
        }
    }

}
