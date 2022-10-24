package com.drone.dispatcher.drone.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class DroneStateValidator extends Validator<Drone, ValidationException> {

    @Override
    protected Mono<Boolean> isValid(Drone parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(Drone::getState)
                .map(Drone.State.IDLE::equals)
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(Drone parameter) {
        return new ValidationException("Drone not available");
    }
}
