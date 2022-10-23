package com.drone.dispatcher.medication.repository;

import com.drone.dispatcher.medication.model.CarriedMedication;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CarriedMedicationRepository extends ReactiveCrudRepository<CarriedMedication, Long> {

    Flux<CarriedMedication> findAllByDroneUuid(String droneUuid);
}
