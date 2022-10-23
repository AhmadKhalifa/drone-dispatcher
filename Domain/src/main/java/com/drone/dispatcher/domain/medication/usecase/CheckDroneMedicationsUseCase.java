package com.drone.dispatcher.domain.medication.usecase;

import com.drone.dispatcher.domain.base.MonoUseCase;
import com.drone.dispatcher.domain.drone.validator.DroneExistsValidator;
import com.drone.dispatcher.domain.medication.dto.CarriedMedicationListResponseDto;
import com.drone.dispatcher.domain.medication.mapper.CarriedMedicationMapper;
import com.drone.dispatcher.domain.medication.repository.CarriedMedicationRepository;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CheckDroneMedicationsUseCase implements MonoUseCase<String, CarriedMedicationListResponseDto> {

    private final CarriedMedicationRepository carriedMedicationRepository;
    private final MedicationRepository medicationRepository;
    private final DroneExistsValidator droneExistsValidator;
    private final CarriedMedicationMapper carriedMedicationMapper;

    @Override
    public Mono<CarriedMedicationListResponseDto> apply(String parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(droneExistsValidator::validate)
                .flatMapMany(carriedMedicationRepository::findAllByDroneUuid)
                .flatMap(carriedMedication -> Mono
                        .just(carriedMedication)
                        .zipWith(medicationRepository.findByUuid(carriedMedication.getMedicationUuid()))
                )
                .map(carriedMedicationMapper::toDto)
                .collectList()
                .map(CarriedMedicationListResponseDto::new);
    }
}
