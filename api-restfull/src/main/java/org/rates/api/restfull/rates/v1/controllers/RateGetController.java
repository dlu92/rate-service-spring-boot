package org.rates.api.restfull.rates.v1.controllers;

import jakarta.servlet.annotation.WebListener;
import lombok.AllArgsConstructor;
import org.rates.api.restfull.rates.v1.mappers.RateEntityToResponse;
import org.rates.api.restfull.rates.v1.responses.RateResponse;
import org.rates.application.rates.find.RateFinder;
import org.rates.domain.rates.RateEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebListener
@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public final class RateGetController {

    private final RateFinder rateFinder;

    @GetMapping("/{id}")
    public ResponseEntity<RateResponse> getById(@PathVariable Long id) {
        RateEntity rate = this.rateFinder.find(id);

        return ResponseEntity.ok(RateEntityToResponse.map(rate));
    }

}
