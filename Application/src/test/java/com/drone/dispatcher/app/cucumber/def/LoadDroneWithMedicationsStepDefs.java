package com.drone.dispatcher.app.cucumber.def;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoadDroneWithMedicationsStepDefs extends CommonDroneStepDefs {

    @When("user requests to load the following medications on drone {string}")
    public void userRequestsToLoadTheFollowingMedicationsOnDrone(String droneId) {
        throw new PendingException();
    }

    @Then("drone {string} state should be {string} then {string}")
    public void droneStateShouldBeThen(String droneId, String intermediateState, String finalState) {
        throw new PendingException();
    }
}
