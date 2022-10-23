package com.drone.dispatcher.domain.drone.repository;

import com.drone.dispatcher.domain.drone.model.Drone;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface DroneRepository extends ReactiveCrudRepository<Drone, Long> {

    Mono<Drone> findByUuid(String uuid);

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    Mono<Boolean> existsByUuid(String uuid);

    Flux<Drone> findAllByState(Drone.State state);

    Mono<Drone> findBySerialNumber(String serialNumber);
}
