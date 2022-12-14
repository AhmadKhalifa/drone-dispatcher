package com.drone.dispatcher.drone.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.drone.repository.DroneRepository;
import com.drone.dispatcher.exception.ConflictException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DroneSerialNumberUniqueValidator extends Validator<DroneRegistrationDto, ConflictException> {

    private final DroneRepository droneRepository;

    @Override
    protected Mono<Boolean> isValid(DroneRegistrationDto parameter) {
        return droneRepository
                .findBySerialNumber(parameter.getSerialNumber())
                .hasElement()
                .map(exists -> !exists);
    }

    @Override
    protected ConflictException getException(DroneRegistrationDto parameter) {
        return new ConflictException("Drone already registered with this serial number");
    }
}
