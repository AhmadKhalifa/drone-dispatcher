package com.drone.dispatcher.domain.medication.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Builder
@Table("carried_medications")
public class CarriedMedication {

    @Id
    private String uuid;
    private String droneUuid;
    private String medicationUuid;
    private int quantity;
}
