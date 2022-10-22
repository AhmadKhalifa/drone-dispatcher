package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class DroneBatteryStatusAuditJobStepDefs extends BaseStepDefs {

    @Given("current time is {string}")
    public void currentTimeIs(String currentTime) {
        throw new PendingException();
    }

    @When("battery audit job starts")
    public void batteryAuditJobStarts() {
        throw new PendingException();
    }

    @Then("the following logs should be saved in battery audit history")
    public void theFollowingLogsShouldBeSavedInBatteryAuditHistory(DataTable expectedJobIterationResultsData) {
        throw new PendingException();
    }
}
