package com.drone.dispatcher.domain.drone.usecase;

import com.drone.dispatcher.base.MonoUseCase;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.drone.mapper.DroneMapper;
import com.drone.dispatcher.domain.drone.model.DroneDto;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.drone.validator.DroneSerialNumberUniqueValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterDroneUseCase implements MonoUseCase<DroneRegistrationDto, DroneDto> {

    private final DroneRepository droneRepository;
    private final DroneSerialNumberUniqueValidator droneSerialNumberUniqueValidator;
    private final DroneMapper droneMapper;

    @Override
    public Mono<DroneDto> apply(DroneRegistrationDto parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(droneSerialNumberUniqueValidator::validate)
                .map(droneMapper::fromDto)
                .flatMap(droneRepository::save)
                .map(droneMapper::toDto);
    }
}
