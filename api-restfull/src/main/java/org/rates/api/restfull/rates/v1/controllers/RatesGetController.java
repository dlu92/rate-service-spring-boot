package org.rates.api.restfull.rates.v1.controllers;

import jakarta.servlet.annotation.WebListener;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.rates.api.restfull.rates.v1.mappers.RateEntityCollectionToResponse;
import org.rates.api.restfull.rates.v1.requests.RateFilterRequest;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.search.RateProductPriceFinderByDate;
import org.rates.domain.rates.RateEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@WebListener
@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public final class RatesGetController {

    private RateProductPriceFinderByDate rateProductPriceFinderByDate;

    @GetMapping("")
    public ResponseEntity<RateResponse[]> getList(@Valid @ModelAttribute RateFilterRequest request) throws ParseException {
        RateEntity[] rates = this.rateProductPriceFinderByDate.search(request.getProductId(), request.getBrandId(), (new SimpleDateFormat("yyyy-MM-dd")).parse(request.getDate()));

        return ResponseEntity.ok(RateEntityCollectionToResponse.map(rates));
    }

}
