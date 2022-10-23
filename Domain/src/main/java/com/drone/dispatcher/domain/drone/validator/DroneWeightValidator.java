package com.drone.dispatcher.domain.drone.validator;

import com.drone.dispatcher.domain.base.Validator;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class DroneWeightValidator extends Validator<DroneRegistrationDto, ValidationException> {

    @Value("${application.validations.maxDroneWeightLimit}")
    private int maxDroneWeightLimit;

    @Override
    protected Mono<Boolean> isValid(DroneRegistrationDto parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(DroneRegistrationDto::getWeightLimit)
                .filter(weight -> weight > 0)
                .map(weightLimit -> weightLimit <= maxDroneWeightLimit)
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(DroneRegistrationDto parameter) {
        String message = parameter.getWeightLimit() > 0 ?
                "Drone max weight is more than max allowed" :
                "Drone max weight can't be zero or negative";
        return new ValidationException(message);
    }
}
