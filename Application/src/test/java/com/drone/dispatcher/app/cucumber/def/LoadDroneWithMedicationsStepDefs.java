package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoadDroneWithMedicationsStepDefs extends BaseStepDefs {

    @When("user requests to load the following medications on drone {string}")
    public void userRequestsToLoadTheFollowingMedicationsOnDrone(String droneId, DataTable medicationsToBeLoaded) {
        throw new PendingException();
    }

    @Then("drone {string} state should be {string} then {string}")
    public void droneStateShouldBeThen(String droneId, String intermediateState, String finalState) {
        throw new PendingException();
    }
}
