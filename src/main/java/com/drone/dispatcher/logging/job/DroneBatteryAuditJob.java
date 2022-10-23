package com.drone.dispatcher.logging.job;

import com.drone.dispatcher.logging.usecase.LogDronesBatteryLevelUseCase;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@AllArgsConstructor
public class DroneBatteryAuditJob {

    private final LogDronesBatteryLevelUseCase logDronesBatteryLevelUseCase;

    @Scheduled(cron = "${application.jobs.DroneBatteryAuditJob.cronExpression}")
    public void tick() {
        log.info("Starting drone battery audit job...");
        logDronesBatteryLevelUseCase.apply().subscribe(
                droneBatteryLog -> log.info("Saved battery status log {}", droneBatteryLog),
                throwable -> log.error("Error saving battery status log: {}", throwable.getMessage(), throwable)
        );
    }
}
