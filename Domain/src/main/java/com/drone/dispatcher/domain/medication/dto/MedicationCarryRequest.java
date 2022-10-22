package com.drone.dispatcher.domain.medication.dto;

import lombok.Data;

import java.util.List;

@Data
public class MedicationCarryRequest {

    private String droneUuid;
    private List<RequestedMedication> medications;

    @Data
    public static class RequestedMedication {
        private String medicationUuid;
        private int quantity;
    }
}
