package com.drone.dispatcher.logging.mapper;

import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.logging.model.DroneBatteryLog;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.UUID;

@Component
public class DroneBatteryLogMapper {

    public DroneBatteryLog fromDrone(Drone drone, LocalDateTime checkedAt) {
        return DroneBatteryLog
                .builder()
                .uuid(UUID.randomUUID().toString())
                .droneUuid(drone.getUuid())
                .percentage(drone.getBatteryCapacity())
                .checkedAt(checkedAt)
                .build();
    }
}
