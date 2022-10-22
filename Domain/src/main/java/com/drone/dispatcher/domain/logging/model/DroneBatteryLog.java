package com.drone.dispatcher.domain.logging.model;

import com.drone.dispatcher.domain.drone.model.Drone;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Table("drone_battery_logs")
public class DroneBatteryLog {

    @Id
    private String uuid;
    private Drone droneUuid;
    private int percentage;
    private LocalDateTime checkedAt;
}
