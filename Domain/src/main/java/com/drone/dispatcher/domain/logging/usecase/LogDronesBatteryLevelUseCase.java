package com.drone.dispatcher.domain.logging.usecase;

import com.drone.dispatcher.domain.base.FluxUseCase;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.logging.mapper.DroneBatteryLogMapper;
import com.drone.dispatcher.domain.logging.model.DroneBatteryLog;
import com.drone.dispatcher.domain.logging.repository.DroneBatteryLogRepository;
import com.drone.dispatcher.domain.util.Clock;
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
