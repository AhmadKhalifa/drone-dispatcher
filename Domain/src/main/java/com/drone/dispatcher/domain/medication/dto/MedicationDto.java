package com.drone.dispatcher.domain.medication.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicationDto {
    private String uuid;
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
}
