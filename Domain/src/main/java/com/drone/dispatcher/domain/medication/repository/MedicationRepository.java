package com.drone.dispatcher.domain.medication.repository;

import com.drone.dispatcher.domain.medication.model.Medication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface MedicationRepository extends ReactiveCrudRepository<Medication, String> {

    Mono<Medication> findByCode(String code);
}
