package com.drone.dispatcher.domain.medication.validator;

import com.drone.dispatcher.domain.base.Validator;
import com.drone.dispatcher.domain.exception.NotFoundException;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class MedicationExistsValidator extends Validator<String, NotFoundException> {

    private final MedicationRepository medicationRepository;

    @Override
    protected Mono<Boolean> isValid(String parameter) {
        return medicationRepository.existsByUuid(parameter);
    }

    @Override
    protected NotFoundException getException(String parameter) {
        return new NotFoundException("Medication not found");
    }
}
