package com.drone.dispatcher.app.cucumber.def;

import com.drone.dispatcher.app.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.domain.drone.dto.DroneDto;
import com.drone.dispatcher.domain.drone.dto.DroneListResponseDto;
import com.drone.dispatcher.domain.drone.model.Drone;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckAvailableDroneStepDefs extends BaseStepDefs {

    @When("user checks for available drones")
    public void userChecksForAvailableDrones() {
        webClient.get("/drones/available");
    }

    @Then("the following drone\\(s) should return")
    public void theFollowingDroneSShouldReturn(DataTable expectedAvailableDrones) {
        webClient
                .responseBody(200)
                .expectBody(DroneListResponseDto.class)
                .consumeWith(results -> {
                    List<String> responseDronesSerialNumbers = Objects.requireNonNull(results.getResponseBody())
                            .getResults()
                            .stream()
                            .map(DroneDto::getSerialNumber)
                            .sorted()
                            .collect(Collectors.toList());
                    List<String> expectedDronesSerialNumbers = readDrones(expectedAvailableDrones)
                            .stream()
                            .map(Drone::getSerialNumber)
                            .sorted()
                            .collect(Collectors.toList());
                    assertThat(responseDronesSerialNumbers)
                            .asList()
                            .containsExactlyElementsOf(expectedDronesSerialNumbers);
                });
    }
}
