package com.drone.dispatcher.domain.medication.validator;

import com.drone.dispatcher.base.Validator;
import com.drone.dispatcher.base.exception.NotFoundException;
import com.drone.dispatcher.domain.medication.dto.MedicationRegistrationDto;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MedicationCodeUniqueValidator extends Validator<MedicationRegistrationDto, NotFoundException> {

    private final MedicationRepository medicationRepository;

    @Override
    protected Mono<Boolean> isValid(MedicationRegistrationDto parameter) {
        return medicationRepository
                .findByCode(parameter.getCode())
                .hasElement()
                .map(exists -> !exists);
    }

    @Override
    protected NotFoundException getException(MedicationRegistrationDto parameter) {
        return new NotFoundException("Medication already registered with this code");
    }
}
