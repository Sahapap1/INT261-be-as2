package sit.int261.beas2.controllers;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sit.int261.beas2.dto.CreateRentalRequest;
import sit.int261.beas2.entities.Rental;
import sit.int261.beas2.service.RentalService;

@RestController
@RequestMapping("/rentals")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;

    @PostMapping
    public ResponseEntity<Rental> createRental(
            @Valid @RequestBody CreateRentalRequest request) {

        Rental rental = rentalService.createRental(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(rental);
    }
}