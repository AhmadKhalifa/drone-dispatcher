package com.drone.dispatcher.app.cucumber.def;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckDroneBatteryStepDefs extends CommonDroneStepDefs {

    @When("user checks for battery level for drone {string}")
    public void userChecksForBatteryLevelForDrone(String droneId) {
        throw new PendingException();
    }

    @Then("the returned battery percentage should be {int}")
    public void theReturnedBatteryPercentageShouldBeBatteryPercentage(int expectedBatteryPercentage) {
        throw new PendingException();
    }
}
