package com.drone.dispatcher.domain.medication.validator;

import com.drone.dispatcher.domain.base.Validator;
import com.drone.dispatcher.domain.exception.ValidationException;
import com.drone.dispatcher.domain.medication.dto.MedicationRegistrationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class MedicationCodeValidator extends Validator<MedicationRegistrationDto, ValidationException> {

    @Value("${application.validations.medicationCodeRegex}")
    private String medicationCodeRegex;

    @Override
    protected Mono<Boolean> isValid(MedicationRegistrationDto parameter) {
        return Mono
                .justOrEmpty(parameter)
                .map(MedicationRegistrationDto::getCode)
                .map(medicationCode -> medicationCode.matches(medicationCodeRegex))
                .defaultIfEmpty(false);
    }

    @Override
    protected ValidationException getException(MedicationRegistrationDto parameter) {
        return new ValidationException("Invalid medication code");
    }
}
