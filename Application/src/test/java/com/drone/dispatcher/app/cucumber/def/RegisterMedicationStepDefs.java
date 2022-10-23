package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.domain.medication.dto.MedicationDto;
import com.drone.dispatcher.domain.medication.dto.MedicationRegistrationDto;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.Objects;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class RegisterMedicationStepDefs extends BaseStepDefs {


    @Given("configured medication name regex is {string}")
    public void configuredMedicationNameRegexIs(String nameRegex) {
        // Do nothing as it's already configured in test profile variables
    }

    @And("configured medication code regex is {string}")
    public void configuredMedicationCodeRegexIs(String codeRegex) {
        // Do nothing as it's already configured in test profile variables
    }

    @When("user registers a new medication with the following details")
    public void userRegistersANewMedicationWithTheFollowingDetails(DataTable newMedicationDetails) {
        MedicationRegistrationDto medicationRegistrationRequestBody = newMedicationDetails
                .asMaps()
                .stream()
                .map(row -> new MedicationRegistrationDto(
                        row.get("Name"),
                        row.get("Code"),
                        Integer.parseInt(row.get("Weight")),
                        scenarioData.uuidOf(row.get("Image ID"))
                ))
                .findFirst()
                .orElseThrow(() -> new IllegalStateException("Invalid medication details"));
        webClient.post("/medications/register", medicationRegistrationRequestBody, MedicationRegistrationDto.class);
    }

    @Then("medication is registered successfully with code {string}")
    public void medicationIsRegisteredSuccessfullyWithCode(String medicationCode) {
        webClient
                .responseBody(200)
                .expectBody(MedicationDto.class)
                .consumeWith(results -> assertThat(medicationCode.equals(
                        Objects.requireNonNull(results.getResponseBody()).getCode())).isTrue()
                );
    }
}
