package com.drone.dispatcher.domain.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationRegistrationDto {
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
}
