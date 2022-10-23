package com.drone.dispatcher.medication.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.exception.ValidationException;
import com.drone.dispatcher.medication.dto.MedicationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MedicationNameValidator extends Validator<MedicationRegistrationDto, ValidationException> {

    @Value("${application.validations.medicationNameRegex}")
    private String medicationNameRegex;

    @Override
    protected Mono<Boolean> isValid(MedicationRegistrationDto parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(MedicationRegistrationDto::getName)
                .map(medicationName -> medicationName.matches(medicationNameRegex))
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(MedicationRegistrationDto parameter) {
        return new ValidationException("Invalid medication name");
    }
}
