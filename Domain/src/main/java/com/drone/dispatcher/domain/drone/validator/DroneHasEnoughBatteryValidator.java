package com.drone.dispatcher.domain.drone.validator;

import com.drone.dispatcher.domain.base.Validator;
import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DroneHasEnoughBatteryValidator extends Validator<String, ValidationException> {

    @Value("${application.validations.minBatteryCapacity}")
    private int minBatteryCapacity;

    private final DroneRepository droneRepository;

    @Override
    protected Mono<Boolean> isValid(String parameter) {
        return droneRepository
                .findByUuid(parameter)
                .map(Drone::getBatteryCapacity)
                .map(batteryCapacity -> batteryCapacity >= minBatteryCapacity);
    }

    @Override
    protected ValidationException getException(String parameter) {
        return new ValidationException("Drone battery low");
    }
}
