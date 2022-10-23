package com.drone.dispatcher.medication.repository;

import com.drone.dispatcher.medication.model.Medication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MedicationRepository extends ReactiveCrudRepository<Medication, Long> {

    Mono<Medication> findByUuid(String uuid);

    @SuppressWarnings("SpringDataRepositoryMethodReturnTypeInspection")
    Mono<Boolean> existsByUuid(String uuid);

    Mono<Medication> findByCode(String code);
}
