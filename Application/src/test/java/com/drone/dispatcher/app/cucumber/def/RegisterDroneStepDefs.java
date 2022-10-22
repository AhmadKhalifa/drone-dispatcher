package com.drone.dispatcher.app.cucumber.def;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class RegisterDroneStepDefs extends CommonDroneStepDefs {

    @Given("configured max allowed serial number length is {int}")
    public void configuredMaxAllowedSerialNumberLengthIs(int configuredMaxAllowedSerialNumberLength) {
        throw new PendingException();
    }

    @And("configured max allowed drone weight is {int} grams")
    public void configuredMaxAllowedDroneWeightIsGrams(int configuredDroneMaxAllowedWeight) {
        throw new PendingException();
    }

    @When("user registers a new drone with the following details")
    public void userRegistersANewDroneWithTheFollowingDetails(DataTable newDroneDetails) {
        throw new PendingException();
    }

    @Then("drone is registered successfully with serial number {string}")
    public void droneIsRegisteredSuccessfullyWithSerialNumber(String droneSerialNumber) {
        throw new PendingException();
    }
}
