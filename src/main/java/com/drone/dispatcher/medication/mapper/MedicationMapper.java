package com.drone.dispatcher.medication.mapper;

import com.drone.dispatcher.medication.dto.MedicationDto;
import com.drone.dispatcher.medication.dto.MedicationRegistrationDto;
import com.drone.dispatcher.medication.model.Medication;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class MedicationMapper {

    public MedicationDto toDto(Medication medication) {
       return MedicationDto
               .builder()
               .uuid(medication.getUuid())
               .name(medication.getName())
               .code(medication.getCode())
               .weight(medication.getWeight())
               .imageUuid(medication.getImageUuid())
               .build();
    }

    public Medication fromDto(MedicationRegistrationDto medicationRegistrationDto) {
       return Medication
               .builder()
               .uuid(UUID.randomUUID().toString())
               .name(medicationRegistrationDto.getName())
               .code(medicationRegistrationDto.getCode())
               .weight(medicationRegistrationDto.getWeight())
               .imageUuid(medicationRegistrationDto.getImageUuid())
               .build();
    }
}
