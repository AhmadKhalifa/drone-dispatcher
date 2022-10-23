package com.drone.dispatcher.cucumber.def;

import com.drone.dispatcher.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.logging.model.DroneBatteryLog;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.test.StepVerifier;

import java.util.List;
import java.util.stream.Collectors;

import static org.mockito.Mockito.doReturn;

public class DroneBatteryStatusAuditJobStepDefs extends BaseStepDefs {

    @Given("current time is {string}")
    public void currentTimeIs(String currentTime) {
        doReturn(toDateTime(currentTime)).when(clock).now();
    }

    @When("battery audit job starts")
    public void batteryAuditJobStarts() {
        droneBatteryAuditJob.tick();
    }

    @Then("the following logs should be saved in battery audit history")
    public void theFollowingLogsShouldBeSavedInBatteryAuditHistory(DataTable expectedJobIterationResultsData) {
        List<DroneBatteryLog> expectedDroneBatteryLogs = expectedJobIterationResultsData
                .asMaps()
                .stream()
                .map(row -> DroneBatteryLog
                        .builder()
                        .droneUuid(scenarioData.uuidOf(row.get("Drone ID")))
                        .percentage(Integer.parseInt(row.get("Battery capacity")))
                        .checkedAt(toDateTime(row.get("Checked at")))
                        .build()
                )
                .collect(Collectors.toList());
        StepVerifier
                .create(droneBatteryLogRepository.findAll().all(logInDb -> expectedDroneBatteryLogs.stream()
                        .anyMatch(expectedLog ->
                                expectedLog.getDroneUuid().equals(logInDb.getDroneUuid()) &&
                                        expectedLog.getPercentage() == (logInDb.getPercentage()) &&
                                        expectedLog.getCheckedAt().equals(logInDb.getCheckedAt())
                        )
                ))
                .expectNext(true)
                .verifyComplete();
    }
}
