package com.drone.dispatcher.domain.medication.usecase;

import com.drone.dispatcher.domain.base.MonoUseCase;
import com.drone.dispatcher.domain.medication.dto.MedicationDto;
import com.drone.dispatcher.domain.medication.dto.MedicationRegistrationDto;
import com.drone.dispatcher.domain.medication.mapper.MedicationMapper;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import com.drone.dispatcher.domain.medication.validator.MedicationCodeUniqueValidator;
import com.drone.dispatcher.domain.medication.validator.MedicationCodeValidator;
import com.drone.dispatcher.domain.medication.validator.MedicationNameValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterMedicationUseCase implements MonoUseCase<MedicationRegistrationDto, MedicationDto> {

    private final MedicationRepository medicationRepository;
    private final MedicationCodeUniqueValidator medicationCodeUniqueValidator;
    private final MedicationNameValidator medicationNameValidator;
    private final MedicationCodeValidator medicationCodeValidator;
    private final MedicationMapper medicationMapper;

    @Override
    public Mono<MedicationDto> apply(MedicationRegistrationDto parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(medicationCodeUniqueValidator::validate)
                .flatMap(medicationNameValidator::validate)
                .flatMap(medicationCodeValidator::validate)
                .map(medicationMapper::fromDto)
                .flatMap(medicationRepository::save)
                .map(medicationMapper::toDto);
    }
}
