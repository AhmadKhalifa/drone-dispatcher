package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.medication.dto.MedicationCarryRequest;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.test.StepVerifier;

import java.util.stream.Collectors;

public class LoadDroneWithMedicationsStepDefs extends BaseStepDefs {

    @When("user requests to load the following medications on drone {string}")
    public void userRequestsToLoadTheFollowingMedicationsOnDrone(String droneId, DataTable medicationsToBeLoaded) {
        MedicationCarryRequest requestBody = new MedicationCarryRequest(
                scenarioData.uuidOf(droneId),
                medicationsToBeLoaded
                        .asMaps()
                        .stream()
                        .map(row -> new MedicationCarryRequest.RequestedMedication(
                                scenarioData.uuidOf(row.get("Medication ID")),
                                Integer.parseInt(row.get("Quantity"))
                        ))
                        .collect(Collectors.toList())
        );
        webClient.post("/medications/load", requestBody, MedicationCarryRequest.class);
    }

    @Then("drone {string} should be loaded with the following medications")
    public void droneIsLoadedWithTheFollowingMedications(String droneId, DataTable carriedMedicationsData) {
        StepVerifier
                .create(carriedMedicationRepository.findAllByDroneUuid(scenarioData.uuidOf(droneId)).collectList())
                .expectNextMatches(carriedMedications ->
                        areCarriedMedicationsTheSame(carriedMedicationsData, carriedMedications)
                )
                .verifyComplete();
    }

    @Then("drone {string} state should be {string}")
    public void droneStateShouldBeThen(String droneId, String finalState) throws InterruptedException {
        Thread.sleep(500);
        StepVerifier
                .create(droneRepository.findByUuid(scenarioData.uuidOf(droneId)))
                .expectNextMatches(drone -> drone.getState().equals(Drone.State.valueOf(finalState)))
                .verifyComplete();
    }

    @Given("configured min battery capacity for working drone is {int} percent")
    public void configuredMinBatteryCapacityForWorkingDroneIsPercent(int batteryPercentage) {
        // Do nothing as it's already configured in test profile variables
    }
}
