package com.drone.dispatcher.domain.logging.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDateTime;

@Data
@Builder
@Table("drone_battery_logs")
public class DroneBatteryLog {

    @Id
    private Long id;
    private String uuid;
    private String droneUuid;
    private int percentage;
    private LocalDateTime checkedAt;
}
