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
public class DroneSerialNumberValidator extends Validator<DroneRegistrationDto, ValidationException> {

    @Value("${application.validations.maxDroneSerialNumberLength}")
    private int maxDroneSerialNumberLength;

    @Override
    protected Mono<Boolean> isValid(DroneRegistrationDto parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(drone -> drone.getSerialNumber().length())
                .map(serialNumberLength -> serialNumberLength <= maxDroneSerialNumberLength)
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(DroneRegistrationDto parameter) {
        return new ValidationException("Drone serial number is too long");
    }
}
