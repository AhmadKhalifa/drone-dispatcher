package com.drone.dispatcher.drone.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DroneModelValidator extends Validator<DroneRegistrationDto, ValidationException> {

    @Override
    protected Mono<Boolean> isValid(DroneRegistrationDto parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(DroneRegistrationDto::getModel)
                .map(Drone.Model::isValidModel)
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(DroneRegistrationDto parameter) {
        return new ValidationException("Invalid drone model");
    }
}
