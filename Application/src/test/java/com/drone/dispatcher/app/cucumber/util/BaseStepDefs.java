package com.drone.dispatcher.app.cucumber.util;


import io.cucumber.java.After;
import io.cucumber.spring.CucumberContextConfiguration;
import lombok.AccessLevel;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.test.context.ActiveProfiles;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureWebTestClient
@CucumberContextConfiguration
@Getter(AccessLevel.PROTECTED)
public abstract class BaseStepDefs {

    @Autowired
    private ScenarioData scenarioData;

    @Autowired
    private WebClient webClient;

    private List<ReactiveCrudRepository<?, ?>> getRepositories() {
        return List.of(

        );
    }

    @After
    public void clearScenario() {
        getRepositories()
                .stream()
                .map(ReactiveCrudRepository::deleteAll)
                .collect(Collectors.toList())
                .forEach(Mono::block);
        scenarioData.clear();
    }
}
