package com.drone.dispatcher.cucumber.def;

import com.drone.dispatcher.cucumber.util.BaseStepDefs;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.stream.Collectors;

public class CommonDroneStepDefs extends BaseStepDefs {

    @Before
    public void clearScenario() {
        getRepositories()
                .stream()
                .map(ReactiveCrudRepository::deleteAll)
                .collect(Collectors.toList())
                .forEach(Mono::block);
        scenarioData.clear();
    }

    @Given("the following drones are registered")
    public void theFollowingDronesAreRegistered(DataTable registeredDronesData) {
        StepVerifier
                .create(droneRepository.saveAll(readDrones(registeredDronesData)).collectList())
                .expectNextMatches(savedDrones -> savedDrones.size() == registeredDronesData.asMaps().size())
                .verifyComplete();
    }

    @Then("an error is returned with status code {int} with a message {string}")
    public void anErrorIsReturnedWithStatusCodeWithAMessage(int expectedErrorCode, String expectedErrorMessage) {
        webClient
                .responseBody(expectedErrorCode)
                .expectBody()
                .jsonPath("$.message").isEqualTo(expectedErrorMessage);
    }

    @And("the following medications are registered")
    public void theFollowingMedicationsAreRegistered(DataTable registeredMedicationsData) {
        StepVerifier
                .create(medicationRepository.saveAll(readMedications(registeredMedicationsData)).collectList())
                .expectNextMatches(medications -> medications.size() == registeredMedicationsData.asMaps().size())
                .verifyComplete();
    }
}
