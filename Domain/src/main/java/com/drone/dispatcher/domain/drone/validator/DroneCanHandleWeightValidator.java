package com.drone.dispatcher.domain.drone.validator;

import com.drone.dispatcher.domain.base.Validator;
import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.exception.ValidationException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;

@Component
@AllArgsConstructor
public class DroneCanHandleWeightValidator extends Validator<Tuple2<Integer, String>, ValidationException> {

    private final DroneRepository droneRepository;

    @Override
    protected Mono<Boolean> isValid(Tuple2<Integer, String> parameter) {
        return droneRepository
                .findByUuid(parameter.getT2())
                .map(Drone::getWeightLimit)
                .map(droneWeightLimit -> droneWeightLimit >= parameter.getT1());
    }

    @Override
    protected ValidationException getException(Tuple2<Integer, String> parameter) {
        return new ValidationException("This drone can't carry these medications");
    }
}
