package com.drone.dispatcher.cucumber.def;

import com.drone.dispatcher.cucumber.util.BaseStepDefs;
import com.drone.dispatcher.drone.model.Drone;
import com.drone.dispatcher.medication.dto.CarriedMedicationDto;
import com.drone.dispatcher.medication.dto.CarriedMedicationListResponseDto;
import com.drone.dispatcher.medication.model.Medication;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CheckDroneMedicationsStepDefs extends BaseStepDefs {

    @Then("drone {string} is loaded with the following medications")
    public void droneIsLoadedWithTheFollowingMedications(String droneId, DataTable carriedMedicationsData) {
        List<Tuple2<String, Integer>> carriedMedications = carriedMedicationsData
                .asMaps()
                .stream()
                .map(row -> Tuples.of(
                        scenarioData.uuidOf(row.get("Medication ID")),
                        Integer.parseInt(row.get("Quantity"))
                ))
                .collect(Collectors.toList());
        StepVerifier
                .create(
                        droneRepository
                                .findByUuid(scenarioData.uuidOf(droneId))
                                .map(Drone::markAsLoaded)
                                .flatMap(droneRepository::save)
                                .flatMapMany(__ -> Flux.fromIterable(carriedMedications))
                                .flatMap(carriedMedication -> {
                                    System.out.printf("Checking for medication %s\n", carriedMedication.getT1());
                                    return medicationRepository
                                            .findByUuid(carriedMedication.getT1())
                                            .doOnSuccess(medication -> System.out.printf("Found medication: %s\n", medication))
                                            .zipWith(Mono.just(carriedMedication.getT2()));
                                })
                                .map(carriedMedicationData -> carriedMedicationMapper.toDto(Tuples.of(
                                        carriedMedicationData.getT1(),
                                        scenarioData.uuidOf(droneId),
                                        carriedMedicationData.getT2()
                                )))
                                .map(carriedMedicationMapper::fromDto)
                                .collectList()
                                .flatMapMany(carriedMedicationRepository::saveAll)
                                .collectList()
                ).expectNextMatches(savedCarriedMedications ->
                        areCarriedMedicationsTheSame(carriedMedicationsData, savedCarriedMedications)
                )
                .verifyComplete();
    }

    @When("user checks for medications carried by drone {string}")
    public void userChecksForMedicationsCarriedByDrone(String droneId) {
        webClient.get(String.format("/medications/drone/%s", scenarioData.uuidOf(droneId)));
    }

    @Then("the following medication\\(s) should return")
    public void theFollowingMedicationSShouldReturn(DataTable expectedMedicationsData) {
        webClient
                .responseBody(200)
                .expectBody(CarriedMedicationListResponseDto.class)
                .consumeWith(results -> {
                    List<String> responseMedicationsCodes = Objects.requireNonNull(results.getResponseBody())
                            .getResults()
                            .stream()
                            .map(CarriedMedicationDto::getCode)
                            .sorted()
                            .collect(Collectors.toList());
                    List<String> expectedMedicationsCodes = readMedications(expectedMedicationsData)
                            .stream()
                            .map(Medication::getCode)
                            .sorted()
                            .collect(Collectors.toList());
                    assertThat(responseMedicationsCodes)
                            .asList()
                            .containsExactlyElementsOf(expectedMedicationsCodes);
                });
    }
}
