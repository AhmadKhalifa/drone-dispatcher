package com.drone.dispatcher.app.cucumber.util;


import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.reactive.server.WebTestClient;

import javax.annotation.PostConstruct;
import java.util.List;

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

    @PostConstruct
    public void initWebClient() {
        webClient = new WebClient(webTestClient, scenarioData);
    }

    protected List<ReactiveCrudRepository<?, ?>> getRepositories() {
        return List.of(

        );
    }
}
