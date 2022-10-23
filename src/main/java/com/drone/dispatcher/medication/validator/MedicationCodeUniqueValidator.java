package com.drone.dispatcher.medication.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.exception.ConflictException;
import com.drone.dispatcher.medication.dto.MedicationRegistrationDto;
import com.drone.dispatcher.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MedicationCodeUniqueValidator extends Validator<MedicationRegistrationDto, ConflictException> {

    private final MedicationRepository medicationRepository;

    @Override
    protected Mono<Boolean> isValid(MedicationRegistrationDto parameter) {
        return medicationRepository
                .findByCode(parameter.getCode())
                .hasElement()
                .map(exists -> !exists);
    }

    @Override
    protected ConflictException getException(MedicationRegistrationDto parameter) {
        return new ConflictException("Medication already registered with this code");
    }
}
