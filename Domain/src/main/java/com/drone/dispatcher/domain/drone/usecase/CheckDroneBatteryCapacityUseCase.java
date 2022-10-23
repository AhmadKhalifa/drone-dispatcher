package com.drone.dispatcher.domain.drone.usecase;

import com.drone.dispatcher.domain.base.MonoUseCase;
import com.drone.dispatcher.domain.drone.dto.BatteryCapacityDto;
import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.drone.validator.DroneExistsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CheckDroneBatteryCapacityUseCase implements MonoUseCase<String, BatteryCapacityDto> {

    private final DroneRepository droneRepository;
    private final DroneExistsValidator droneExistsValidator;

    @Override
    public Mono<BatteryCapacityDto> apply(String parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(droneExistsValidator::validate)
                .flatMap(droneRepository::findByUuid)
                .map(Drone::getBatteryCapacity)
                .map(BatteryCapacityDto::new);
    }
}