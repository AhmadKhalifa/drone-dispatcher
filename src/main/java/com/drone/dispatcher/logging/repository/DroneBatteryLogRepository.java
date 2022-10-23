package com.drone.dispatcher.logging.repository;

import com.drone.dispatcher.logging.model.DroneBatteryLog;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DroneBatteryLogRepository extends ReactiveCrudRepository<DroneBatteryLog, Long> {

}
