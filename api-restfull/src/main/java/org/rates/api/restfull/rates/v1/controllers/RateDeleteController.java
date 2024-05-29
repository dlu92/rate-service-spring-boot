package org.rates.api.restfull.rates.v1.controllers;

import jakarta.servlet.annotation.WebListener;
import lombok.AllArgsConstructor;
import org.rates.application.rates.detele.RateDeleted;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@WebListener
@RestController
@RequestMapping("/api/v1/rates")
@AllArgsConstructor
public final class RateDeleteController {

    private final RateDeleted rateDeleted;

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        this.rateDeleted.delete(id);

        return ResponseEntity.noContent().build();
    }

}
