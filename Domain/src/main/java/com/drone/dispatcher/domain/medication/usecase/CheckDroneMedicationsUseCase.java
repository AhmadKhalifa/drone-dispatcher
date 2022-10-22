package com.drone.dispatcher.domain.medication.usecase;

import com.drone.dispatcher.base.FluxUseCase;
import com.drone.dispatcher.domain.drone.validator.DroneExistsValidator;
import com.drone.dispatcher.domain.medication.dto.CarriedMedicationDto;
import com.drone.dispatcher.domain.medication.mapper.CarriedMedicationMapper;
import com.drone.dispatcher.domain.medication.repository.CarriedMedicationRepository;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CheckDroneMedicationsUseCase implements FluxUseCase<String, CarriedMedicationDto> {

    private final CarriedMedicationRepository carriedMedicationRepository;
    private final MedicationRepository medicationRepository;
    private final DroneExistsValidator droneExistsValidator;
    private final CarriedMedicationMapper carriedMedicationMapper;

    @Override
    public Flux<CarriedMedicationDto> apply(String parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(droneExistsValidator::validate)
                .flatMapMany(carriedMedicationRepository::findAllByDroneUuid)
                .flatMap(carriedMedication -> Mono
                        .just(carriedMedication)
                        .zipWith(medicationRepository.findById(carriedMedication.getMedicationUuid()))
                )
                .map(carriedMedicationMapper::toDto);
    }
}
