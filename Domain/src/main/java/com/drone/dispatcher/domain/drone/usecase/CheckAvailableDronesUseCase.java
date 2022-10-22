package com.drone.dispatcher.domain.drone.usecase;

import com.drone.dispatcher.base.FluxUseCase;
import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
@AllArgsConstructor
public class CheckAvailableDronesUseCase implements FluxUseCase<Void, Drone> {

    private final DroneRepository droneRepository;

    public Flux<Drone> apply() {
        return apply(null);
    }

    @Override
    public Flux<Drone> apply(Void parameters) {
        return droneRepository.findAllByState(Drone.State.IDLE);
    }
}
