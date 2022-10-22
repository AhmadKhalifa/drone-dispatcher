package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CheckAvailableDroneStepDefs extends BaseStepDefs {

    @When("user checks for available drones")
    public void userChecksForAvailableDrones() {
        throw new PendingException();
    }

    @Then("the following drone\\(s) should return")
    public void theFollowingDroneSShouldReturn(DataTable expectedAvailableDrones) {
        throw new PendingException();
    }
}
