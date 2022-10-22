package com.drone.dispatcher.domain.drone.model;

import com.drone.dispatcher.domain.drone.model.Drone;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DroneDto {
    private String uuid;
    private String serialNumber;
    private int weightLimit;
    private int batteryCapacity;
    private Drone.Model model;
    private Drone.State state;
}
