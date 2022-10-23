package com.drone.dispatcher.domain.drone.controller;

import com.drone.dispatcher.domain.base.Controller;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.drone.usecase.CheckAvailableDronesUseCase;
import com.drone.dispatcher.domain.drone.usecase.CheckDroneBatteryCapacityUseCase;
import com.drone.dispatcher.domain.drone.usecase.RegisterDroneUseCase;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@AllArgsConstructor
@RequestMapping("/drones")
public class DroneController extends Controller {

    private final CheckAvailableDronesUseCase checkAvailableDronesUseCase;
    private final CheckDroneBatteryCapacityUseCase checkDroneBatteryCapacityUseCase;
    private final RegisterDroneUseCase registerDroneUseCase;

    @GetMapping("/available")
    public Mono<ResponseEntity<?>> getAvailableDrones() {
        return response(checkAvailableDronesUseCase.apply());
    }

    @GetMapping("/{droneUuid}/battery")
    public Mono<ResponseEntity<?>> getBatteryCapacity(@PathVariable("droneUuid") String droneUuid) {
        return response(checkDroneBatteryCapacityUseCase.apply(droneUuid));
    }

    @PostMapping("/register")
    public Mono<ResponseEntity<?>> register(@RequestBody DroneRegistrationDto droneRegistrationDto) {
        return response(registerDroneUseCase.apply(droneRegistrationDto));
    }
}
