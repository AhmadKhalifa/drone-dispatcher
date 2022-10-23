package com.drone.dispatcher.domain.drone.usecase;

import com.drone.dispatcher.domain.base.MonoUseCase;
import com.drone.dispatcher.domain.drone.dto.DroneDto;
import com.drone.dispatcher.domain.drone.dto.DroneRegistrationDto;
import com.drone.dispatcher.domain.drone.mapper.DroneMapper;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.drone.validator.DroneModelValidator;
import com.drone.dispatcher.domain.drone.validator.DroneSerialNumberUniqueValidator;
import com.drone.dispatcher.domain.drone.validator.DroneSerialNumberValidator;
import com.drone.dispatcher.domain.drone.validator.DroneWeightValidator;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class RegisterDroneUseCase implements MonoUseCase<DroneRegistrationDto, DroneDto> {

    private final DroneRepository droneRepository;
    private final DroneSerialNumberUniqueValidator droneSerialNumberUniqueValidator;
    private final DroneSerialNumberValidator droneSerialNumberValidator;
    private final DroneWeightValidator droneWeightValidator;
    private final DroneModelValidator droneModelValidator;
    private final DroneMapper droneMapper;

    @Override
    public Mono<DroneDto> apply(DroneRegistrationDto parameters) {
        return Mono
                .justOrEmpty(parameters)
                .flatMap(droneSerialNumberUniqueValidator::validate)
                .flatMap(droneSerialNumberValidator::validate)
                .flatMap(droneWeightValidator::validate)
                .flatMap(droneModelValidator::validate)
                .map(droneMapper::fromDto)
                .flatMap(droneRepository::save)
                .map(droneMapper::toDto);
    }
}
