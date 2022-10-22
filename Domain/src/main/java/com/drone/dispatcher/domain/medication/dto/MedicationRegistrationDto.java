package com.drone.dispatcher.domain.medication.dto;

import lombok.Data;

@Data
public class MedicationRegistrationDto {
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
}
