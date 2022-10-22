package com.drone.dispatcher.domain.drone.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.base.exception.NotFoundException;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DroneSerialNumberUniqueValidator extends Validator<DroneRegistrationDto, NotFoundException> {

    private final DroneRepository droneRepository;

    @Override
    protected Mono<Boolean> isValid(DroneRegistrationDto parameter) {
        return droneRepository
                .findBySerialNumber(parameter.getSerialNumber())
                .hasElement()
                .map(exists -> !exists);
    }

    @Override
    protected NotFoundException getException(DroneRegistrationDto parameter) {
        return new NotFoundException("Drone already registered with this serial number");
    }
}
