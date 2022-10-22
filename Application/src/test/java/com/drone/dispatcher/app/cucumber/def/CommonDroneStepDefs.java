package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;

public class CommonDroneStepDefs extends BaseStepDefs {

    @Given("the following drones are registered")
    public void theFollowingDronesAreRegistered(DataTable registeredDronesData) {
        throw new PendingException();
    }

    @Then("an error is returned with status code {int} with a message {string}")
    public void anErrorIsReturnedWithStatusCodeWithAMessage(int expectedErrorCode, String expectedErrorMessage) {
        throw new PendingException();
    }

    @And("the following medications are registered")
    public void theFollowingMedicationsAreRegistered(DataTable registeredMedicationsData) {
        throw new PendingException();
    }
}
