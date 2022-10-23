package com.drone.dispatcher.domain.drone.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class DroneRegistrationDto {
    private String serialNumber;
    private int weightLimit;
    private String model;
}
