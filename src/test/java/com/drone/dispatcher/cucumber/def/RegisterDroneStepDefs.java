package com.drone.dispatcher.cucumber.def;

import com.drone.dispatcher.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.drone.dto.DroneDto;
import com.drone.dispatcher.drone.dto.DroneRegistrationDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterDroneStepDefs extends BaseStepDefs {

    @Given("configured max allowed serial number length is {int}")
    public void configuredMaxAllowedSerialNumberLengthIs(int configuredMaxAllowedSerialNumberLength) {
        // Do nothing as it's already configured in test profile variables
    }

    @And("configured max allowed drone weight is {int} grams")
    public void configuredMaxAllowedDroneWeightIsGrams(int configuredDroneMaxAllowedWeight) {
        // Do nothing as it's already configured in test profile variables
    }

    @When("user registers a new drone with the following details")
    public void userRegistersANewDroneWithTheFollowingDetails(DataTable newDroneDetails) {
        DroneRegistrationDto droneRegistrationRequestBody = newDroneDetails
                .asMaps()
                .stream()
                .map(row -> new DroneRegistrationDto(
                        row.get("Serial number"),
                        Integer.parseInt(row.get("Weight limit")),
                        row.get("Model")
                ))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid drone details"));
        webClient.post("/drones/register", droneRegistrationRequestBody, DroneRegistrationDto.class);
    }

    @Then("drone is registered successfully with serial number {string}")
    public void droneIsRegisteredSuccessfullyWithSerialNumber(String droneSerialNumber) {
        webClient
                .responseBody(200)
                .expectBody(DroneDto.class)
                .consumeWith(results -> assertThat(droneSerialNumber.equals(
                        Objects.requireNonNull(results.getResponseBody()).getSerialNumber())).isTrue()
                );
    }
}
