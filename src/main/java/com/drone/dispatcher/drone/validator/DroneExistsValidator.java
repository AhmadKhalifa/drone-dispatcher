package com.drone.dispatcher.drone.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.drone.repository.DroneRepository;
import com.drone.dispatcher.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DroneExistsValidator extends Validator<String, NotFoundException> {

    private final DroneRepository droneRepository;

    @Override
    protected Mono<Boolean> isValid(String parameter) {
        return droneRepository.existsByUuid(parameter);
    }

    @Override
    protected NotFoundException getException(String parameter) {
        return new NotFoundException("Drone not found");
    }
}
