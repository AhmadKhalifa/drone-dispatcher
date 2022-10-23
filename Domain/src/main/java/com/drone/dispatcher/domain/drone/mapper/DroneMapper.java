package com.drone.dispatcher.domain.drone.mapper;

import com.drone.dispatcher.domain.drone.dto.DroneDto;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.drone.model.Drone;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class DroneMapper {

    public Drone fromDto(DroneRegistrationDto droneDto) {
        return Drone
                .builder()
                .uuid(UUID.randomUUID().toString())
                .serialNumber(droneDto.getSerialNumber())
                .weightLimit(droneDto.getWeightLimit())
                .batteryCapacity(100)
                .model(Drone.Model.valueOf(droneDto.getModel()))
                .state(Drone.State.IDLE)
                .build();
    }

    public DroneDto toDto(Drone drone) {
        return DroneDto
                .builder()
                .uuid(drone.getUuid())
                .serialNumber(drone.getSerialNumber())
                .weightLimit(drone.getWeightLimit())
                .batteryCapacity(drone.getBatteryCapacity())
                .model(drone.getModel())
                .state(drone.getState())
                .build();
    }
}
