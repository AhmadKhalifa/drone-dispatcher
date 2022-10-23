package com.drone.dispatcher.drone.usecase;

import com.drone.dispatcher.base.MonoUseCase;
import com.drone.dispatcher.drone.dto.DroneListResponseDto;
import com.drone.dispatcher.drone.mapper.DroneMapper;
import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.drone.repository.DroneRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class CheckAvailableDronesUseCase implements MonoUseCase<Void, DroneListResponseDto> {

    private final DroneRepository droneRepository;
    private final DroneMapper droneMapper;

    public Mono<DroneListResponseDto> apply() {
        return apply(null);
    }

    @Override
    public Mono<DroneListResponseDto> apply(Void parameters) {
        return droneRepository
                .findAllByState(Drone.State.IDLE)
                .map(droneMapper::toDto)
                .collectList()
                .map(DroneListResponseDto::new);
    }
}
