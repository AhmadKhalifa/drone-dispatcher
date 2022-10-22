package com.drone.dispatcher.domain.drone.dto;

import com.drone.dispatcher.domain.drone.model.Drone;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class DroneRegistrationDto {
    private String serialNumber;
    private int weightLimit;
    private Drone.Model model;
}
