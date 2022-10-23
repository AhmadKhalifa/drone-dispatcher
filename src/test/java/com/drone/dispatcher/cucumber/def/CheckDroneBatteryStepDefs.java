package com.drone.dispatcher.cucumber.def;

import com.drone.dispatcher.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.drone.dto.BatteryCapacityDto;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckDroneBatteryStepDefs extends BaseStepDefs {

    @When("user checks for battery level for drone {string}")
    public void userChecksForBatteryLevelForDrone(String droneId) {
        webClient.get(String.format("/drones/%s/battery", scenarioData.uuidOf(droneId)));
    }

    @Then("the returned battery percentage should be {int}")
    public void theReturnedBatteryPercentageShouldBeBatteryPercentage(int expectedBatteryPercentage) {
        webClient
                .responseBody(200)
                .expectBody(BatteryCapacityDto.class)
                .consumeWith(results -> {
                    BatteryCapacityDto batteryCapacity = results.getResponseBody();
                    assert batteryCapacity != null;
                    assertThat(batteryCapacity.getBatteryCapacity()).isEqualTo(expectedBatteryPercentage);
                });
    }
}
