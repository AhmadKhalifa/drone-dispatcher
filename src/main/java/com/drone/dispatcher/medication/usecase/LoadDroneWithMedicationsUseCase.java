package com.drone.dispatcher.medication.usecase;

import com.drone.dispatcher.base.MonoUseCase;
import com.drone.dispatcher.drone.dto.DroneDto;
import com.drone.dispatcher.drone.mapper.DroneMapper;
import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.drone.repository.DroneRepository;
import com.drone.dispatcher.drone.validator.DroneCanHandleWeightValidator;
import com.drone.dispatcher.drone.validator.DroneExistsValidator;
import com.drone.dispatcher.drone.validator.DroneHasEnoughBatteryValidator;
import com.drone.dispatcher.medication.dto.CarriedMedicationDto;
import com.drone.dispatcher.medication.dto.MedicationCarryRequest;
import com.drone.dispatcher.medication.mapper.CarriedMedicationMapper;
import com.drone.dispatcher.medication.repository.CarriedMedicationRepository;
import com.drone.dispatcher.medication.repository.MedicationRepository;
import com.drone.dispatcher.medication.validator.MedicationExistsValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

@Component
@AllArgsConstructor
public class LoadDroneWithMedicationsUseCase implements MonoUseCase<MedicationCarryRequest, DroneDto> {

    private final DroneRepository droneRepository;
    private final CarriedMedicationRepository carriedMedicationRepository;
    private final MedicationRepository medicationRepository;
    private final DroneExistsValidator droneExistsValidator;
    private final MedicationExistsValidator medicationExistsValidator;
    private final DroneHasEnoughBatteryValidator droneHasEnoughBatteryValidator;
    private final DroneCanHandleWeightValidator droneCanHandleWeightValidator;
    private final CarriedMedicationMapper carriedMedicationMapper;
    private final DroneMapper droneMapper;

    @Override
    @Transactional
    public Mono<DroneDto> apply(MedicationCarryRequest parameters) {
        return Mono
                .justOrEmpty(parameters)
                .map(MedicationCarryRequest::getDroneUuid)
                .flatMap(droneExistsValidator::validate)
                .flatMap(droneHasEnoughBatteryValidator::validate)
                .flatMapMany(__ -> Flux.fromIterable(parameters.getMedications()))
                .flatMap(requestedMedication ->
                        Flux
                                .just(requestedMedication.getMedicationUuid())
                                .flatMap(medicationExistsValidator::validate)
                                .flatMap(medicationRepository::findByUuid)
                                .map(medication -> Tuples.of(
                                        medication, parameters.getDroneUuid(), requestedMedication.getQuantity()
                                ))
                                .map(carriedMedicationMapper::toDto)
                )
                .collectList()
                .flatMap(carriedMedications ->
                        Mono
                                .just(carriedMedications)
                                .map(CarriedMedicationDto::getTotalWeight)
                                .zipWith(Mono.just(parameters.getDroneUuid()))
                                .flatMap(droneCanHandleWeightValidator::validate)
                                .map(Tuple2::getT2)
                                .flatMap(droneRepository::findByUuid)
                                .map(Drone::markAsLoading)
                                .flatMap(droneRepository::save)
                                .map(__ -> carriedMedications)
                )
                .map(carriedMedicationMapper::fromDto)
                .flatMapMany(carriedMedicationRepository::saveAll)
                .collectList()
                .map(__ -> parameters.getDroneUuid())
                .flatMap(droneRepository::findByUuid)
                .map(Drone::markAsLoaded)
                .flatMap(droneRepository::save)
                .map(droneMapper::toDto);
    }
}
