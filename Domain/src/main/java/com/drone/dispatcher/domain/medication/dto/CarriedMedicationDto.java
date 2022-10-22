package com.drone.dispatcher.domain.medication.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class CarriedMedicationDto {
    private String droneUuid;
    private String uuid;
    private String name;
    private String code;
    private int weight;
    private String imageUuid;
    private int quantity;



    public static int getTotalWeight(List<CarriedMedicationDto> carriedMedications) {
        return carriedMedications
                .stream()
                .map(carriedMedication -> carriedMedication.weight * carriedMedication.quantity)
                .reduce(Integer::sum)
                .orElse(0);
    }
}
