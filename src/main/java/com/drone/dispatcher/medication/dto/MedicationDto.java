package com.drone.dispatcher.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MedicationDto {
    private String uuid;
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
}
