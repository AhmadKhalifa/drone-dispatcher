package com.drone.dispatcher.medication.usecase;

import com.drone.dispatcher.base.MonoUseCase;
import com.drone.dispatcher.drone.validator.DroneExistsValidator;
import com.drone.dispatcher.medication.dto.CarriedMedicationListResponseDto;
import com.drone.dispatcher.medication.mapper.CarriedMedicationMapper;
import com.drone.dispatcher.medication.repository.CarriedMedicationRepository;
import com.drone.dispatcher.medication.repository.MedicationRepository;
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
