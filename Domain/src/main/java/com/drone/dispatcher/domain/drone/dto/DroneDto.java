package com.drone.dispatcher.domain.drone.dto;

import com.drone.dispatcher.domain.drone.model.Drone;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DroneDto {
    private String uuid;
    private String serialNumber;
    private int weightLimit;
    private int batteryCapacity;
    private Drone.Model model;
    private Drone.State state;
}
