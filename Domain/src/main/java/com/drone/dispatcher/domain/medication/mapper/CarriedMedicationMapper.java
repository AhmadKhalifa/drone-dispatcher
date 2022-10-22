package com.drone.dispatcher.domain.medication.mapper;

import com.drone.dispatcher.domain.medication.dto.CarriedMedicationDto;
import com.drone.dispatcher.domain.medication.model.CarriedMedication;
import com.drone.dispatcher.domain.medication.model.Medication;
import org.springframework.stereotype.Component;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Component
public class CarriedMedicationMapper {

    public CarriedMedicationDto toDto(Tuple2<CarriedMedication, Medication> carriedMedicationData) {
        return CarriedMedicationDto
                .builder()
                .droneUuid(carriedMedicationData.getT1().getDroneUuid())
                .uuid(carriedMedicationData.getT2().getUuid())
                .name(carriedMedicationData.getT2().getName())
                .code(carriedMedicationData.getT2().getCode())
                .weight(carriedMedicationData.getT2().getWeight())
                .imageUuid(carriedMedicationData.getT2().getImageUuid())
                .quantity(carriedMedicationData.getT1().getQuantity())
                .build();
    }

    public CarriedMedicationDto toDto(Tuple3<Medication, String, Integer> carriedMedicationData) {
        return CarriedMedicationDto
                .builder()
                .droneUuid(carriedMedicationData.getT2())
                .uuid(carriedMedicationData.getT1().getUuid())
                .name(carriedMedicationData.getT1().getName())
                .code(carriedMedicationData.getT1().getCode())
                .weight(carriedMedicationData.getT1().getWeight())
                .imageUuid(carriedMedicationData.getT1().getImageUuid())
                .quantity(carriedMedicationData.getT3())
                .build();
    }

    public CarriedMedication fromDto(CarriedMedicationDto carriedMedicationData) {
        return CarriedMedication
                .builder()
                .uuid(UUID.randomUUID().toString())
                .droneUuid(carriedMedicationData.getDroneUuid())
                .medicationUuid(carriedMedicationData.getUuid())
                .quantity(carriedMedicationData.getQuantity())
                .build();
    }

    public List<CarriedMedication> fromDto(List<CarriedMedicationDto> carriedMedications) {
        return carriedMedications.stream().map(this::fromDto).collect(Collectors.toList());
    }
}
