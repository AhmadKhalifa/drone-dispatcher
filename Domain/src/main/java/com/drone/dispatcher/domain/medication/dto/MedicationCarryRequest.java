package com.drone.dispatcher.domain.medication.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicationCarryRequest {

    private String droneUuid;
    private List<RequestedMedication> medications;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RequestedMedication {
        private String medicationUuid;
        private int quantity;
    }
}
