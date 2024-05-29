package org.rates.api.restfull.rates.v1.controllers;

import jakarta.servlet.annotation.WebListener;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.rates.api.restfull.rates.v1.mappers.RateEntityToResponse;
import org.rates.api.restfull.rates.v1.mappers.RateRequestToEntity;
import org.rates.api.restfull.rates.v1.requests.RateRequest;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.update.RateUpdated;
import org.rates.domain.rates.RateEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebListener
@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public final class RatePatchController {

    private final RateUpdated rateUpdated;

    @PatchMapping("/{id}")
    public ResponseEntity<RateResponse> update(@PathVariable Long id, @Valid @RequestBody RateRequest request) {
        RateEntity rate = this.rateUpdated.update(id, RateRequestToEntity.map(request));

        return ResponseEntity.ok(RateEntityToResponse.map(rate));
    }

}
