package com.drone.dispatcher.logging.usecase;

import com.drone.dispatcher.base.FluxUseCase;
import com.drone.dispatcher.drone.repository.DroneRepository;
import com.drone.dispatcher.logging.mapper.DroneBatteryLogMapper;
import com.drone.dispatcher.logging.model.DroneBatteryLog;
import com.drone.dispatcher.logging.repository.DroneBatteryLogRepository;
import com.drone.dispatcher.util.Clock;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class LogDronesBatteryLevelUseCase implements FluxUseCase<Void, DroneBatteryLog> {

    private final DroneRepository droneRepository;
    private final DroneBatteryLogRepository droneBatteryLogRepository;
    private final DroneBatteryLogMapper droneBatteryLogMapper;
    private final Clock clock;

    public Flux<DroneBatteryLog> apply() {
        return apply(null);
    }

    @Override
    public Flux<DroneBatteryLog> apply(Void parameters) {
        return droneRepository
                .findAll()
                .map(drone -> droneBatteryLogMapper.fromDrone(drone, clock.now()))
                .collectList()
                .flatMapMany(droneBatteryLogRepository::saveAll);
    }
}
