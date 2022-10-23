package com.drone.dispatcher.app.cucumber.util;


import com.drone.dispatcher.domain.drone.model.Drone;
import com.drone.dispatcher.domain.drone.repository.DroneRepository;
import com.drone.dispatcher.domain.logging.job.DroneBatteryAuditJob;
import com.drone.dispatcher.domain.logging.repository.DroneBatteryLogRepository;
import com.drone.dispatcher.domain.medication.mapper.CarriedMedicationMapper;
import com.drone.dispatcher.domain.medication.model.CarriedMedication;
import com.drone.dispatcher.domain.medication.model.Medication;
import com.drone.dispatcher.domain.medication.repository.CarriedMedicationRepository;
import com.drone.dispatcher.domain.medication.repository.MedicationRepository;
import com.drone.dispatcher.domain.util.Clock;
import io.cucumber.datatable.DataTable;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@CucumberContextConfiguration
public abstract class BaseStepDefs {

    @Autowired
    WebTestClient webTestClient;

    @Autowired
    protected ScenarioData scenarioData;

    protected WebClient webClient;

    @Autowired
    protected DroneRepository droneRepository;

    @Autowired
    protected MedicationRepository medicationRepository;

    @Autowired
    protected CarriedMedicationRepository carriedMedicationRepository;

    @Autowired
    protected DroneBatteryLogRepository droneBatteryLogRepository;

    @Autowired
    protected CarriedMedicationMapper carriedMedicationMapper;

    @Autowired
    protected Clock clock;

    @Autowired
    protected DroneBatteryAuditJob droneBatteryAuditJob;

    protected DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");

    protected LocalDateTime toDateTime(String hour) {
        return LocalTime.parse(hour).atDate(LocalDate.now());
    }

    @PostConstruct
    public void initWebClient() {
        webClient = new WebClient(webTestClient, scenarioData);
    }

    protected List<ReactiveCrudRepository<?, ?>> getRepositories() {
        return List.of(
                droneBatteryLogRepository,
                carriedMedicationRepository,
                medicationRepository,
                droneRepository
        );
    }

    public List<Drone> readDrones(DataTable dronesData) {
        return dronesData
                .asMaps()
                .stream()
                .map(row -> Drone
                        .builder()
                        .uuid(scenarioData.uuidOf(row.get("ID")))
                        .serialNumber(row.get("Serial number"))
                        .weightLimit(Integer.parseInt(row.get("Weight limit")))
                        .batteryCapacity(Integer.parseInt(row.get("Battery capacity")))
                        .model(Drone.Model.valueOf(row.get("Model")))
                        .state(Drone.State.valueOf(row.get("State")))
                        .build()
                )
                .collect(Collectors.toList());
    }

    public List<Medication> readMedications(DataTable medicationsData) {
        return medicationsData
                .asMaps()
                .stream()
                .map(row -> Medication
                        .builder()
                        .uuid(scenarioData.uuidOf(row.get("ID")))
                        .name(row.get("Name"))
                        .code(row.get("Code"))
                        .weight(Integer.parseInt(row.get("Weight")))
                        .imageUuid(scenarioData.uuidOf(row.get("Image ID")))
                        .build()
                )
                .collect(Collectors.toList());
    }

    protected boolean areCarriedMedicationsTheSame(
            DataTable carriedMedicationsData,
            List<CarriedMedication> carriedMedications
    ) {
        Set<String> carriedMedicationsInDb = carriedMedications
                .stream()
                .map(carriedMedication -> String.format(
                        "%s-%d",
                        carriedMedication.getMedicationUuid(),
                        carriedMedication.getQuantity()
                )).collect(Collectors.toSet());
        Set<String> expectedCarriedMedications = carriedMedicationsData
                .asMaps()
                .stream()
                .map(row -> String.format(
                        "%s-%d",
                        scenarioData.uuidOf(row.get("Medication ID")),
                        Integer.parseInt(row.get("Quantity"))
                ))
                .collect(Collectors.toSet());
        return carriedMedicationsInDb.containsAll(expectedCarriedMedications) &&
                expectedCarriedMedications.containsAll(carriedMedicationsInDb);
    }
}
