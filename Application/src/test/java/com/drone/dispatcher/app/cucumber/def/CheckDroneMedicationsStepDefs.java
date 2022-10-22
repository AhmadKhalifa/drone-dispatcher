package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckDroneMedicationsStepDefs extends BaseStepDefs {

    @Given("drone {string} is loaded with the following medications")
    public void droneIsLoadedWithTheFollowingMedications(String droneId) {
        throw new PendingException();
    }

    @When("user checks for medications carried by drone {string}")
    public void userChecksForMedicationsCarriedByDrone(String droneId) {
        throw new PendingException();
    }

    @Then("the following medication\\(s) should return")
    public void theFollowingMedicationSShouldReturn(DataTable expectedMedicationsData) {
        throw new PendingException();
    }
}
