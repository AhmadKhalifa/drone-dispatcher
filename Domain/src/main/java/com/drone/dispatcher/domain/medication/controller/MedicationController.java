package com.drone.dispatcher.domain.medication.controller;

import com.drone.dispatcher.domain.base.Controller;
import com.drone.dispatcher.domain.medication.dto.MedicationCarryRequest;
import com.drone.dispatcher.domain.medication.dto.MedicationRegistrationDto;
import com.drone.dispatcher.domain.medication.usecase.CheckDroneMedicationsUseCase;
import com.drone.dispatcher.domain.medication.usecase.LoadDroneWithMedicationsUseCase;
import com.drone.dispatcher.domain.medication.usecase.RegisterMedicationUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/medications")
public class MedicationController extends Controller {

    private final CheckDroneMedicationsUseCase checkDroneMedicationsUseCase;
    private final LoadDroneWithMedicationsUseCase loadDroneWithMedicationsUseCase;
    private final RegisterMedicationUseCase registerMedicationUseCase;

    @GetMapping("/drone/{droneUuid}")
    public Mono<ResponseEntity<?>> getCarriedMedications(
            @PathVariable("droneUuid") String droneUuid
    ) {
        return response(checkDroneMedicationsUseCase.apply(droneUuid));
    }

    @PostMapping("/load")
    public Mono<ResponseEntity<?>> loadMedications(
            @RequestBody MedicationCarryRequest medicationCarryRequest
    ) {
        return response(loadDroneWithMedicationsUseCase.apply(medicationCarryRequest));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(
            @RequestBody MedicationRegistrationDto medicationRegistrationDto
    ) {
        return response(registerMedicationUseCase.apply(medicationRegistrationDto));
    }
}
