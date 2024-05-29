package org.rates.api.restfull.rates.v1.controllers;

import jakarta.servlet.annotation.WebListener;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.rates.api.restfull.rates.v1.mappers.RateEntityToResponse;
import org.rates.api.restfull.rates.v1.mappers.RateRequestToEntity;
import org.rates.api.restfull.rates.v1.requests.RateRequest;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.create.RateCreator;
import org.rates.domain.rates.RateEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@WebListener
@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public final class RatePostController {

    private final RateCreator rateCreator;

    @PostMapping("")
    public ResponseEntity<RateResponse> create(@Valid @RequestBody RateRequest request) {
        RateEntity rate = this.rateCreator.create(RateRequestToEntity.map(request));

        return ResponseEntity.ok(RateEntityToResponse.map(rate));
    }

}
